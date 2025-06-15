# 02 - Estructura del Frontend

Este módulo documenta la arquitectura del frontend del sistema **Kizuna Gourmet**, construido completamente en **Vue 3** con soporte de Vuetify, Pinia y WebSocket.

El objetivo de este frontend es ofrecer una interfaz moderna y dinámica para todos los roles del sistema (cliente, cocinero, administrador), interactuando en tiempo real con el backend mediante REST y STOMP WebSocket.

---

## Estructura de carpetas

La estructura del directorio `src/` está modularizada por dominio funcional. Las imágenes utilizadas se almacenan en la carpeta `public/` en la raíz del proyecto, accesibles directamente desde la vista sin importar el módulo.

```
📁 src
├── App.vue                   # Componente raíz de la app
├── main.ts                   # Punto de entrada principal
├── shims-vue.d.ts            # Soporte TypeScript para Vue
│
├── assets/                   # Recursos estáticos internos
│   ├── logo.png, logo.svg
│   ├── category/             # Iconos de categorías
│   └── productos/            # Imágenes de productos
│
├── layouts/                 # Layouts base reutilizables
│   ├── AdminLayout.vue
│   ├── KitchenLayout.vue
│   └── MainLayout.vue
│
├── modules/                 # Dominio modularizado
│   ├── admin/               # Vista admin: menú y dashboard
│   ├── auth/                # Login, registro, perfil y seguridad
│   ├── home/                # Vista inicial pública
│   ├── kitchen/             # Panel de cocina en tiempo real
│   ├── orders/              # Pedido, carrito y estado
│   ├── products/            # Productos, menús, categorías
│   └── users/               # Gestión de usuario extendida
│
├── plugins/                # Inicialización de Pinia, Vuetify...
│   ├── pinia.ts
│   ├── vuetify.ts
│   └── webfontloader.ts
│
├── router/                 # Ruteo principal y guards
│   ├── guards.ts
│   └── index.ts
│
└── shared/                 # Utilidades y servicios comunes
    ├── composables/        # useKitchenSocket, useNewOrderSocket
    ├── constants/          # api-roles.ts, api-routes.ts
    ├── services/           # WebSocket managers y api.ts
    └── utils/              # utilidades generales (imagen, etc.)
```

Cada módulo (`orders`, `products`, etc.) incluye:

* `components/`: Vistas reutilizables.
* `views/`: Vistas principales de navegación.
* `services/`: Lógica de acceso a la API.
* `store/`: Gestión de estado vía Pinia.
* `types/`: Tipado DTO.

---

## Conexión con el backend

La comunicación entre el frontend y el backend de Kizuna Gourmet se realiza mediante **Axios**, configurado en un cliente compartido (`shared/services/api.ts`). Este cliente centralizado gestiona automáticamente la inclusión del token JWT, la gestión de errores y redirecciones según el estado HTTP.

### Configuración global de Axios

```ts
const api = axios.create({
  baseURL: process.env.VITE_API_BASE_URL,
  timeout: 10000,
})

api.interceptors.request.use(config => {
  const token = localStorage.getItem('authToken')
  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
})

api.interceptors.response.use(
  response => response,
  error => {
    const status = error?.response?.status

    if (status === 401) {
      useAuthStore().logout()
    }

    if (status === 403) {
      router.push('/forbidden')
    }

    return Promise.reject(error)
  }
)
```

### Ejemplo de uso: `authApi`

```ts
export const authApi = {
  login: (email: string, password: string) =>
    api.post(`${API_ROUTES.AUTH}/login`, { email, password }).then(r => r.data),

  register: (email: string, password: string) =>
    api.post(`${API_ROUTES.AUTH}/register`, { email, password }).then(r => r.data),

  validate: () =>
    api.get(`${API_ROUTES.AUTH}/validate`).then(r => r.data)
}
```

El uso de `API_ROUTES` garantiza que las rutas se mantengan centralizadas, legibles y fácilmente modificables.

---

## Comunicación en tiempo real (WebSocket)

El frontend de Kizuna Gourmet incorpora comunicación bidireccional en tiempo real mediante **WebSocket** usando STOMP + SockJS. Esto permite a cocineros y clientes ver los cambios de estado de pedidos sin necesidad de refrescar.

### Conexión WebSocket modular

Se implementan dos conectores principales:

* Uno para **cocina** (`/ws-kitchen`): escucha cambios de estado de pedidos desde `/topic/order-status`
* Otro para **clientes** (`/ws-orders`): detecta nuevos pedidos en `/topic/orders/new`

Ambos sockets son gestionados por módulos dedicados (`orderWebSocketManager.ts` y `kitchenWebSocketManager.ts`) que exponen una función para conectar y obtener el cliente activo. Esta conexión es reutilizable desde cualquier parte del frontend.

### Composables reactivos

Los sockets se encapsulan en composables Vue (`useOrderSocket`, `useNewOrderSocket`) que:

* Se conectan automáticamente al montarse el componente
* Se suscriben al canal correspondiente
* Actualizan un `ref` reactivo con el último evento recibido
* Cancelan la suscripción automáticamente al desmontar

Esto permite que el estado global (`kitchenStore`, `orderStore`, etc.) reaccione a estos eventos y actualice la interfaz en tiempo real.

---

## Conexión con el backend

La comunicación entre el frontend y el backend de Kizuna Gourmet se realiza mediante **Axios**, configurado en un cliente compartido (`shared/services/api.ts`). Este cliente centralizado gestiona automáticamente la inclusión del token JWT, la gestión de errores y redirecciones según el estado HTTP.

### Configuración global de Axios

```ts
const api = axios.create({
  baseURL: process.env.VITE_API_BASE_URL,
  timeout: 10000,
})

api.interceptors.request.use(config => {
  const token = localStorage.getItem('authToken')
  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
})

api.interceptors.response.use(
  response => response,
  error => {
    const status = error?.response?.status

    if (status === 401) {
      useAuthStore().logout()
    }

    if (status === 403) {
      router.push('/forbidden')
    }

    return Promise.reject(error)
  }
)
```

### Ejemplo de uso: `authApi`

```ts
export const authApi = {
  login: (email: string, password: string) =>
    api.post(`${API_ROUTES.AUTH}/login`, { email, password }).then(r => r.data),

  register: (email: string, password: string) =>
    api.post(`${API_ROUTES.AUTH}/register`, { email, password }).then(r => r.data),

  validate: () =>
    api.get(`${API_ROUTES.AUTH}/validate`).then(r => r.data)
}
```

El uso de `API_ROUTES` garantiza que las rutas se mantengan centralizadas, legibles y fácilmente modificables.

---

## Comunicación en tiempo real (WebSocket)

El frontend de Kizuna Gourmet incorpora comunicación bidireccional en tiempo real mediante **WebSocket** usando STOMP + SockJS. Esto permite a cocineros y clientes ver los cambios de estado de pedidos sin necesidad de refrescar.

### Conexión WebSocket modular

Se implementan dos conectores principales:

* Uno para **cocina** (`/ws-kitchen`): escucha cambios de estado de pedidos desde `/topic/order-status`
* Otro para **clientes** (`/ws-orders`): detecta nuevos pedidos en `/topic/orders/new`

Ambos sockets son gestionados por módulos dedicados (`orderWebSocketManager.ts` y `kitchenWebSocketManager.ts`) que exponen una función para conectar y obtener el cliente activo. Esta conexión es reutilizable desde cualquier parte del frontend.

### Composables reactivos

Los sockets se encapsulan en composables Vue (`useOrderSocket`, `useNewOrderSocket`) que:

* Se conectan automáticamente al montarse el componente
* Se suscriben al canal correspondiente
* Actualizan un `ref` reactivo con el último evento recibido
* Cancelan la suscripción automáticamente al desmontar

Esto permite que el estado global (`kitchenStore`, `orderStore`, etc.) reaccione a estos eventos y actualice la interfaz en tiempo real.

---

## Sistema de rutas y control de acceso

El enrutador de la aplicación se gestiona con `vue-router`, configurado en modo `history`. Las rutas están estructuradas por layouts y protegidas con metadatos que controlan la autenticación y los roles requeridos.

### Estructura del router

- Las rutas públicas incluyen `/`, `/login`, `/register`.
- Las rutas privadas están anidadas dentro de layouts como:
  - `MainLayout` (cliente)
  - `KitchenLayout` (cocinero)
  - `AdminLayout` (administrador)

Cada ruta puede tener `meta.requiresAuth` y `meta.requiredRole`, lo que permite definir protecciones a nivel declarativo.

### Sistema de guardia de navegación

El archivo `router/guards.ts` implementa un guard global con las siguientes responsabilidades:

- Si la ruta requiere login (`requiresAuth`) y el usuario no está autenticado, redirige a `/login`.
- Si la ruta requiere un rol específico (`requiredRole`) y el usuario no lo tiene, redirige a `/forbidden`.
- Si la ruta es solo para invitados (`onlyGuest`) y el usuario está autenticado, redirige a `/`.
- Si el usuario es `COOK`, fuerza que solo pueda navegar en `/kitchen` y `/forbidden`.

Este sistema garantiza una experiencia coherente y segura para todos los roles, sin permitir acceso cruzado entre áreas protegidas.

> El guard también intenta recuperar el usuario si hay un token válido pero aún no está cargado (`authStore.fetchUser()`).

## Sistema de diseño con Vuetify

La aplicación utiliza Vuetify como sistema de diseño visual basado en Material Design 3. Su configuración se realiza de forma centralizada en el archivo `plugins/vuetify.ts`, donde se definen los temas de color y el sistema de iconos.

### Temas personalizados

Se definen **cuatro temas** de color adaptados a los distintos contextos de la app:

- `light`: Tema general para usuarios (predeterminado)
- `dark`: Alternativa oscura (usada en desarrollo o dispositivos con modo oscuro)
- `kitchen`: Tema claro, limpio y funcional para el panel del cocinero
- `admin`: Tema sobrio con tonos neutros para el panel de administración

Cada tema define colores específicos para:

- `primary`, `secondary`, `accent`
- `background`, `surface`
- `info`, `success`, `warning`, `error`
- `onBackground`, `onSurface`
- Elementos decorativos como `drawerTitle`

### Iconos

El set de iconos utilizado es `mdi` (Material Design Icons), configurado como conjunto por defecto.

```ts
icons: {
  defaultSet: 'mdi',
}
```

## Gestión de estado con Pinia

La aplicación utiliza **Pinia** como gestor de estado centralizado, ofreciendo una solución reactiva, modular y sencilla de mantener.

### Configuración global

Pinia se inicializa en `plugins/pinia.ts` y se extiende con el plugin `pinia-plugin-persistedstate`, lo que permite persistir automáticamente el estado relevante (ej. sesión de usuario) en `localStorage`:

```ts
const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)
```

Esto permite que stores como `authStore` recuerden el usuario logueado incluso tras cerrar la pestaña.

---

### Ejemplo de store: `useProductStore`

Un store típico define:

* **Estado local**: `products`, `isLoading`, `error`
* **Acciones asíncronas**: `fetchProducts`
* **Uso de `ref()`** para reactividad inmediata

Este patrón se replica en todos los dominios clave:

* `authStore`: sesión y roles
* `orderStore`: pedidos y estado
* `cartStore`: carrito de compra
* `kitchenStore`: pedidos activos en cocina
* `productStore`, `menuStore`, etc.

Cada store se aloja dentro del módulo correspondiente (`modules/product/store/productStore.ts`, etc.), facilitando la escalabilidad.

<div style="page-break-after: always;"></div>