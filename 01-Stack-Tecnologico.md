<div style="page-break-after: always;"></div>

# 01 - Stack Tecnológico del Proyecto

El sistema **Kizuna Gourmet** se basa en una arquitectura moderna de microservicios desplegados con Docker Compose, desarrollados con tecnologías robustas tanto en el backend como en el frontend. A continuación, se documenta el stack tecnológico empleado, empezando por la parte de backend.

---

## Backend

El backend está compuesto por múltiples microservicios independientes, desarrollados en **Java 17** con **Spring Boot 3.4.5**, y estructurados de manera que cada uno puede evolucionar y escalar de forma autónoma.

### Tecnologías clave:

| Componente              | Tecnología                                                  |
| ----------------------- | ----------------------------------------------------------- |
| Lenguaje                | Java 17                                                     |
| Framework principal     | Spring Boot 3.4.5                                           |
| Arquitectura            | Microservicios                                              |
| Gestión de dependencias | Maven (con estructura independiente por microservicio)      |
| Comunicación            | Feign Client + Eureka Service Discovery                     |
| Seguridad               | Spring Security + JWT (con filtros y roles)                 |
| Configuración central   | Spring Cloud Config Server (con archivos `msvc-*.yml`)      |
| Base de datos           | MySQL (único contenedor con múltiples bases)                |
| ORM                     | JPA (Hibernate)                                             |
| WebSocket               | Spring WebSocket + STOMP + SockJS                           |
| Contenedores            | Docker / Docker Compose                                     |
| Monitorización interna  | Spring Boot Actuator (habilitado pero no usado activamente) |

### Detalles adicionales

* **Descubrimiento de servicios**:

  * Se utiliza **Eureka Server** como registro de servicios.
  * Cada microservicio es cliente Eureka y se registra automáticamente.

* **Configuración externa**:

  * Un microservicio `config-server` centraliza la configuración.
  * Cada microservicio posee su archivo dedicado (`msvc-[nombre].yml`).

* **Seguridad**:

  * El sistema utiliza JWT para la autenticación.
  * Se implementan filtros personalizados (`JwtAuthenticationFilter`) y roles (`@PreAuthorize`) para proteger endpoints.

* **Comunicación entre servicios**:

  * Mediante Feign, con balanceo interno gracias a Eureka y rutas expuestas por el Gateway (`/api/...`).

* **Persistencia**:

  * Cada microservicio usa **JPA** con **Hibernate**.
  * Un solo contenedor MySQL aloja múltiples bases de datos (una por microservicio), optimizando los recursos.

* **WebSocket**:

  * Utilizado en microservicios como `order` y `kitchen` para emitir eventos en tiempo real.
  * Implementación mediante **STOMP** y **SockJS**.

* **Despliegue**:

  * Orquestado mediante `docker-compose up`.
  * Cada microservicio ejecuta un **script de arranque** que verifica la disponibilidad de sus dependencias haciendo llamadas a endpoints `/health` expuestos por los demás.

---

<div style="page-break-after: always;"></div>

## 🐳 Infraestructura y despliegue con Docker

La arquitectura del sistema está contenida y orquestada usando Docker Compose, permitiendo levantar toda la infraestructura y microservicios con un solo comando.

### Estructura General del `docker-compose.yml`

El archivo principal de orquestación se encuentra en la raíz del proyecto y define los siguientes servicios:

* `mysql`: Contenedor único de base de datos compartida con múltiples esquemas.
* `config-server`: Servidor centralizado de configuración Spring Cloud Config.
* `eureka-server`: Servicio de descubrimiento de microservicios.
* Servicios de negocio:

  * `auth-service`
  * `product-service`
  * `user-service`
  * `order-service`
  * `kitchen-service`
* `gateway`: Puerta de entrada unificada a todos los servicios (Spring Cloud Gateway).

Cada microservicio depende explícitamente de `eureka-server`, y este a su vez depende del `config-server`, el cual depende de `mysql`, garantizando así un orden correcto de arranque.

Los contenedores están conectados por una red bridge común: `spring-cloud-network`.

### Base de datos unificada

Aunque inicialmente se planteó tener una base de datos independiente por microservicio, se optó finalmente por **un único contenedor de MySQL**, donde cada microservicio gestiona su propio esquema. Esto reduce el consumo de recursos, facilitando el despliegue local en entornos limitados.

```yaml
environment:
  MYSQL_ROOT_PASSWORD: 12345
volumes:
  - ./mysql/init.sql:/docker-entrypoint-initdb.d/init.sql
```

El script de inicialización crea los esquemas necesarios.

### Dockerfile común de microservicios de entidad

Todos los microservicios siguen un patrón de construcción en dos etapas:

1. **Build con Maven**:

   * Se construye el `.jar` con `-DskipTests` para acelerar el proceso.

2. **Runtime con Eclipse Temurin**:

   * Se instala `netcat` para ejecutar el script `wait-for.sh`.
   * Se expone el puerto correspondiente al microservicio.
   * Se espera a que el `eureka-server` esté operativo antes de lanzar la aplicación.

```dockerfile
ENTRYPOINT ["./wait-for.sh", "eureka-server", "8761", "java", "-jar", "app.jar"]
```

### Script de espera `wait-for.sh`

Este script garantiza que el servicio Eureka esté listo antes de iniciar el microservicio dependiente:

```sh
until nc -z "$host" "$port"; do
  echo "⏳ Esperando a que $host:$port esté disponible..."
  sleep 1
done
```

Esto es clave para evitar errores de conexión prematuros entre servicios dependientes.

### Script especial de espera para Gateway

El `gateway` requiere que todos los microservicios de negocio estén operativos antes de poder iniciar. Para ello, se emplea un script adicional `wait-for-all.sh`:

```sh
wait_for_check "Auth Service" auth-service 8094
wait_for_check "Product Service" product-service 8090
wait_for_check "User Service" user-service 8092
wait_for_check "Order Service" order-service 8091
wait_for_check "Kitchen Service" kitchen-service 8093
```

Este script hace peticiones HTTP a los endpoints `/check` de cada servicio hasta recibir una respuesta satisfactoria (`OK`). De esta forma se asegura que todos los servicios estén completamente listos antes de arrancar el gateway.

### Comando de arranque global

El sistema completo se puede levantar con:

```bash
docker compose up --build
```

Esto compila los servicios y asegura que todas las dependencias estén disponibles antes de iniciar cada microservicio. Todos los puertos clave (8080 para Gateway, 8761 para Eureka, 8888 para Config, 3306 para MySQL, etc.) se exponen para facilitar el acceso desde frontend y herramientas externas.

<div style="page-break-after: always;"></div>

## Stack Tecnológico - Frontend

El frontend del sistema de pedidos Kizuna Gourmet está desarrollado en **Vue 3** utilizando **TypeScript** en todos sus módulos. Se estructura de forma modular bajo `src/modules`, permitiendo escalar el desarrollo en secciones como `auth`, `products`, `orders`, `kitchen`, `admin`, etc.

### Framework y herramientas principales

* **Vue 3**: Framework base del frontend.
* **Vue Router 4**: Controla la navegación entre vistas. Se define en `router/index.ts` e incluye guardas de navegación (`router/guards.ts`) para verificar tokens JWT y roles de acceso.
* **Pinia 3**: Sistema de gestión de estado global. Los stores se alojan por dominio en cada módulo (`authStore`, `productStore`, `orderStore`, etc.).
* **Vuetify 3.8.7**: Sistema de diseño visual. Se configura en `plugins/vuetify.ts` y se utilizan múltiples temas (`light`, `dark`, `kitchen`, `admin`). Los estilos se aplican principalmente a través de clases Vuetify (`d-flex`, `text-center`, `text-accent`, etc.).
* **TypeScript**: Tipado fuerte en todos los módulos, tanto en lógica como en tipos de datos (`*.types.ts`).
* **Axios**: Utilizado para llamadas HTTP al backend. Está centralizado en `shared/services/api.ts`, donde se configuran los interceptores para añadir el JWT a cada solicitud y gestionar errores como 401 o 403.
* **STOMP + SockJS**: Para WebSocket. El sistema se conecta a `/ws-orders` y `/ws-kitchen` desde los composables `useOrderSocket.ts` y `useNewOrderSocket.ts`. Permite actualizaciones en tiempo real sobre estados de pedido y nuevos pedidos.

### Persistencia de sesión

* El token JWT se almacena en `localStorage`.
* Se usa `pinia-plugin-persistedstate` para persistir automáticamente el estado de autenticación entre sesiones.

### Estructura de carpetas destacadas

* `src/modules`: Contiene los dominios funcionales (auth, orders, kitchen, admin...). Cada uno tiene sus propios `components`, `services`, `store`, `types` y `views`.
* `src/shared`: Contiene lógica reutilizable (WebSocket managers, constantes, composables comunes, utilidades de imágenes...).
* `src/layouts`: Plantillas globales para cada tipo de usuario (`MainLayout.vue`, `KitchenLayout.vue`, `AdminLayout.vue`).
* `src/plugins`: Configuración de Vuetify, Pinia y carga de fuentes.

### Despliegue y entorno

* El proyecto se ejecuta localmente mediante `npm run serve`.
* Las imágenes se sirven desde la carpeta `/public`.
* La URL base del backend se gestiona a través de la variable de entorno `VITE_API_BASE_URL`, usada por Axios.

Este stack proporciona una base sólida, escalable y bien organizada, orientada a una experiencia de usuario fluida y conectada en tiempo real con el backend de microservicios.

<div style="page-break-after: always;"></div>