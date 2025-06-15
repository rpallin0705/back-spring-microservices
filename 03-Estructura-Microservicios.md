# 03 - Estructura de Microservicios

El sistema **Kizuna Gourmet** está compuesto por un conjunto de microservicios independientes, desplegados de forma separada y orquestados mediante Docker Compose. Originalmente concebidos dentro de un monorepo bajo un `pom.xml` padre, fueron desacoplados para permitir una arquitectura más flexible, donde cada microservicio gestiona su ciclo de vida de forma autónoma.

La comunicación entre microservicios se realiza principalmente a través de **Feign Client** con resolución de rutas basada en **Eureka**, y todas las llamadas externas se canalizan mediante el **API Gateway** de Spring Cloud, que define rutas con el prefijo `/api/{servicio}/...`.

A continuación se describe la estructura de cada microservicio, sus responsabilidades, endpoints principales, modelos clave y dependencias.

![estructura](umls/msvc.png)

---

## Microservicios principales

### microservice-config

* **Descripción**: Servicio de configuración centralizada para el resto del sistema.
* **Responsabilidades**:

  * Cargar propiedades desde un repositorio remoto (Git/local).
  * Servir configuraciones a otros servicios al arrancar.
* **Ruta gateway**: No aplica (se comunica internamente).

### microservice-eureka

* **Descripción**: Servicio de descubrimiento de instancias mediante Eureka Server.
* **Responsabilidades**:

  * Registrar y mantener el estado de los servicios vivos.
  * Facilitar balanceo y descubrimiento para Feign.
* **Ruta gateway**: No aplica (uso interno).

### microservice-gateway

* **Descripción**: Punto de entrada único para todas las peticiones externas al backend.
* **Responsabilidades**:

  * Enrutamiento dinámico de peticiones a servicios registrados en Eureka.
  * Aplicación de filtros de seguridad o logging si es necesario.
* **Ejemplos de rutas configuradas**:

  * `/api/products/**` → `msvc-product`
  * `/api/orders/**` → `msvc-order`
  * `/api/users/**` → `msvc-user`
  * `/api/auth/**` → `msvc-auth`

---

## microservice-product

* **Descripción**: Gestiona toda la lógica relacionada con productos, categorías y menús disponibles en el sistema.

* **Responsabilidades**:

  * Crear, editar y listar productos del restaurante.
  * Definir categorías y asociarlas a productos.
  * Componer menús formados por productos con cantidades definidas.

* **Ruta Gateway**: `/api/products/**`

### Modelos principales

#### `ProductEntity`

* `id`: Identificador del producto.
* `name`: Nombre del producto.
* `description`: Descripción detallada.
* `price`: Precio unitario.
* `available`: Disponibilidad (booleano).
* `category`: Categoría asociada al producto.

#### `CategoryEntity`

* `id`: Identificador de la categoría.
* `name`: Nombre de la categoría (ej: Sushi, Ramen).
* `description`: Descripción textual.

#### `MenuEntity` *(a complementar si se desea)*

* `id`: Identificador del menú.
* `name`: Nombre del menú.
* `description`: Breve descripción del conjunto de productos.
* `totalPrice`: Precio total del menú.

#### `MenuProductEntity`

* `id`: Clave primaria.
* `menu`: Menú al que pertenece esta entrada.
* `producto`: Producto referenciado.
* `cantidad`: Cantidad de unidades de ese producto en el menú.

### Endpoints

### Endpoints - microservice-product

| Método | Ruta                | Descripción                              | Requiere Rol ADMIN |
|--------|---------------------|-------------------------------------------|---------------------|
| GET    | /api/products       | Obtener todos los productos               | No                  |
| GET    | /api/products/{id}  | Obtener un producto por ID                | No                  |
| POST   | /api/products       | Crear un nuevo producto                   | Sí                  |
| PUT    | /api/products/{id}  | Actualizar un producto existente          | Sí                  |
| DELETE | /api/products/{id}  | Eliminar un producto                      | Sí                  |
| GET    | /api/menus          | Obtener todos los menús                   | No                  |
| GET    | /api/menus/{id}     | Obtener un menú por ID                    | No                  |
| POST   | /api/menus          | Crear un nuevo menú                       | Sí                  |
| PUT    | /api/menus/{id}     | Actualizar un menú existente              | Sí                  |
| DELETE | /api/menus/{id}     | Eliminar un menú                          | Sí                  |
| GET    | /api/categories     | Obtener todas las categorías              | No                  |
| GET    | /api/categories/{id}| Obtener una categoría por ID              | No                  |
| POST   | /api/categories     | Crear una nueva categoría                 | Sí                  |
| DELETE | /api/categories/{id}| Eliminar una categoría                    | Sí                  |

---

## microservice-auth

* **Descripción**: Se encarga de gestionar la autenticación de usuarios y la asignación de roles mediante JWT. Es un microservicio crítico para la seguridad del sistema.

* **Responsabilidades**:

  * Autenticación con credenciales y emisión de tokens JWT.
  * Verificación de sesiones entrantes mediante filtros JWT.
  * Gestión de usuarios del sistema.
  * Asignación y consulta de roles.

* **Ruta Gateway**: `/api/auth/**`

### Modelos principales

#### `UserEntity`

* `id`: Identificador único del usuario.
* `email`: Correo electrónico, único.
* `password`: Contraseña encriptada.
* `roles`: Conjunto de roles asociados al usuario (relación `@ManyToMany`).

#### `RoleEntity`

* `id`: Identificador del rol.
* `name`: Nombre único del rol (ej: `ADMIN`, `USER`, `COOK`).
* `users`: Conjunto de usuarios que poseen este rol (inverso de la relación).

### Endpoints

### Endpoints - microservice-auth

| Método | Ruta                   | Descripción                                                                 | Requiere Autenticación |
|--------|------------------------|------------------------------------------------------------------------------|--------------------------|
| POST   | /api/auth/register     | Registrar un nuevo usuario con email y contraseña                           | No                       |
| POST   | /api/auth/login        | Autenticación con email y contraseña. Devuelve token JWT                    | No                       |
| POST   | /api/auth/login/device| Autenticación para dispositivos físicos mediante ID y secret                | No                       |
| GET    | /api/auth/validate     | Validar un token JWT y obtener la información del usuario autenticado       | Sí                       |

>**Nota:** El endpoint `/login/device` se diseñó para una posible funcionalidad que permitiría asociar dispositivos físicos (como tablets en mesas del restaurante) a clientes, permitiendo que estos hicieran pedidos desde el propio dispositivo de su mesa. Esta función no ha sido terminada ni desplegada en producción.


---

## microservice-user

* **Descripción**: Maneja la información extendida de los usuarios autenticados, como datos personales y direcciones físicas asociadas. Este servicio se complementa con `auth-service`, que gestiona las credenciales y roles.

* **Responsabilidades**:

  * Almacenar y consultar perfiles de usuario.
  * Asociar múltiples direcciones físicas a un usuario.
  * Registrar fechas de creación y actualización para trazabilidad.

* **Ruta Gateway**: `/api/users/**`

### Modelos principales

#### `UserEntity`

* `id`: Identificador del usuario.
* `name`: Nombre visible del usuario.
* `authEmail`: Correo del usuario autenticado (referenciado desde `auth-service`).
* `phone`: Número de teléfono.
* `createdAt`, `updatedAt`: Timestamps para trazabilidad.
* `addresses`: Lista de direcciones asociadas.

#### `AddressEntity`

* `id`: Identificador de la dirección.
* `street`: Calle.
* `city`: Ciudad.
* `postalCode`: Código postal.
* `country`: País.
* `user`: Usuario al que pertenece esta dirección.

### Endpoints

#### User

| Método | Ruta                                       | Descripción                                                                   | Requiere Autenticación |
|--------|--------------------------------------------|--------------------------------------------------------------------------------|--------------------------|
| GET    | /api/users                                 | Obtener todos los usuarios (solo ADMIN)                                       | Sí (ADMIN)               |
| GET    | /api/users/{id}                            | Obtener un usuario por su ID                                                  | Sí (ADMIN)               |
| GET    | /api/users/me                              | Obtener datos del usuario autenticado                                         | Sí                       |
| GET    | /api/users/me/details/{addressId}          | Obtener detalles del usuario actual para un pedido concreto                   | Sí                       |
| GET    | /api/users/details/{userId}/{addressId}    | Obtener detalles de otro usuario y dirección para el pedido                   | Sí                       |
| POST   | /api/users                                  | Crear nuevo perfil de usuario (datos extendidos, no login)                   | No                       |
| DELETE | /api/users/{id}                            | Eliminar un usuario por ID                                                    | Sí (ADMIN)               |
| PATCH  | /api/users/me                              | Actualizar datos del usuario autenticado                                      | Sí                       |

### Address

| Método | Ruta                 | Descripción                                          | Requiere Autenticación |
|--------|----------------------|-------------------------------------------------------|--------------------------|
| GET    | /api/addresses        | Obtener todas las direcciones (solo ADMIN)           | Sí (ADMIN)               |
| GET    | /api/addresses/{id}   | Obtener una dirección específica                     | Sí                       |
| POST   | /api/addresses        | Crear nueva dirección                                 | Sí                       |
| DELETE | /api/addresses/{id}   | Eliminar una dirección por ID (solo ADMIN)           | Sí (ADMIN)               |

---

## microservice-order

* **Descripción**: Responsable de gestionar los pedidos realizados por los clientes, incluyendo su creación, estado, contenido y trazabilidad histórica. Este servicio actúa como núcleo del sistema, coordinando entre usuarios, cocina y productos.

* **Responsabilidades**:

  * Registrar nuevos pedidos con sus productos o menús asociados.
  * Controlar el estado actual del pedido y su evolución.
  * Almacenar el historial de cambios de estado.
  * Calcular precios totales y tiempos estimados de preparación.

* **Ruta Gateway**: `/api/orders/**`

### Modelos principales

#### `OrderEntity`

* `id`: Identificador único del pedido.
* `userId`: ID del usuario que realiza el pedido.
* `deviceId`: Identificador del dispositivo (opcional, útil para notificaciones).
* `addressId`: Dirección de envío asociada.
* `createdAt`: Fecha y hora de creación del pedido.
* `status`: Estado actual (`PENDING`, `IN_PREPARATION`, `READY`, etc.).
* `totalPrice`: Precio total calculado.
* `items`: Lista de productos o menús incluidos.
* `estimatedPreparationTime`: Tiempo estimado en minutos.

#### `OrderItemEntity`

* `id`: Clave primaria del ítem.
* `productId`: Producto individual solicitado (puede ser null si es menú).
* `menuId`: Menú solicitado (puede ser null si es producto).
* `quantity`: Cantidad de unidades.
* `price`: Precio subtotal del ítem.
* `order`: Referencia al pedido principal.

#### `OrderStatusHistoryEntity`

* `id`: Identificador del registro histórico.
* `status`: Estado por el que pasó el pedido.
* `changedAt`: Fecha y hora del cambio.
* `order`: Pedido al que pertenece el registro.

### Endpoints


| Método | Ruta                                         | Descripción                                                                 | Requiere Autenticación |
|--------|----------------------------------------------|------------------------------------------------------------------------------|--------------------------|
| GET    | /api/orders                                  | Obtener todos los pedidos con datos enriquecidos                            | No                       |
| GET    | /api/orders/{id}                             | Obtener un pedido específico con información completa                       | No                       |
| POST   | /api/orders                                   | Crear un nuevo pedido                                                       | Sí (USER)                |
| DELETE | /api/orders/{id}                             | Eliminar un pedido por ID                                                   | Sí                       |
| GET    | /api/orders/{orderId}/status-history         | Obtener historial de estados de un pedido                                   | No                       |
| GET    | /api/orders/kitchen                          | Obtener pedidos activos para la cocina (pendientes, en preparación, etc.)   | Sí (COOK)                |
| PUT    | /api/orders/kitchen/{id}/status              | Actualizar el estado de un pedido desde cocina                              | Sí (COOK)                |

>**Nota:** Este microservicio fue un pequeño reto, ya que depende directamente de los microservicios `user`, `product` y `address` para construir objetos `OrderDTO` enriquecidos. Cada pedido no solo contiene información de producto o menú, sino también los datos del usuario y su dirección de envío. La resolución de estos datos se realiza internamente mediante clientes Feign.

#### Ejemplo de respuesta enriquecida (`OrderDTO`)

El siguiente JSON representa un pedido completo tal como lo devuelve el endpoint `/api/orders/{id}`, con datos agregados de `user-service` y `product-service`:

```json
{
  "id": 13,
  "addressId": 1,
  "userId": 3,
  "deviceId": null,
  "status": "CREATED",
  "totalPrice": 8.2,
  "createdAt": "2025-06-15T11:31:39.805194638",
  "estimatedPreparationTime": 14,
  "user": {
    "id": 3,
    "name": "cliente1",
    "email": "cliente1@gmail.com",
    "phone": "+34333333333",
    "address": {
      "id": 1,
      "street": "Calle Sakura 12",
      "city": "Madrid",
      "postalCode": "28001",
      "country": "España",
      "userId": 3
    }
  },
  "items": [
    {
      "productId": 2,
      "menuId": null,
      "quantity": 3,
      "price": 2.0,
      "product": {
        "id": 2,
        "name": "Maki de atún",
        "description": "Rollo de arroz y alga con atún en su interior.",
        "price": 2.0,
        "available": true,
        "categoryName": "Sushi"
      },
      "menu": null
    },
    {
      "productId": 4,
      "menuId": null,
      "quantity": 1,
      "price": 2.2,
      "product": {
        "id": 4,
        "name": "Uramaki de aguacate",
        "description": "Rollo invertido con aguacate y pepino.",
        "price": 2.2,
        "available": true,
        "categoryName": "Sushi"
      },
      "menu": null
    }
  ]
}
```

#### Construcción de `OrderDTO` enriquecido

El objeto `OrderDTO` es un JSON enriquecido que agrupa información clave desde distintos microservicios, permitiendo al frontend obtener todos los datos de un pedido en una única respuesta. 

Esto se logra mediante un servicio interno que:

1. **Filtra pedidos según el rol** del usuario autenticado (ADMIN, COOK, USER):
   - **ADMIN** ve todos los pedidos.
   - **COOK** solo los del día actual.
   - **USER** únicamente los suyos propios.

2. **Recupera datos externos**:
   - Llama a `product-service` para obtener los detalles de los productos mediante Feign.
   - Llama a `product-service` para los menús si aplica.
   - Llama a `user-service` para obtener el nombre, email, teléfono y dirección del usuario.

3. **Construye un `OrderDTO` completo**:
   - Combina los datos originales del pedido (`OrderEntity`) con los productos, menús y detalles del usuario (objeto `UserDetailsDTO`).
   - Esto evita múltiples llamadas desde el cliente y acelera la carga de vistas.

> Esta lógica se ejecuta dentro del método `getAllFullOrders()` del `OrderService`, utilizando clientes Feign para cada microservicio externo.

---

## microservice-kitchen

* **Descripción**: Este microservicio se encarga exclusivamente de la gestión operativa de cocina. Permite consultar los pedidos pendientes, actualizar su estado y notificar a los clientes mediante WebSocket cuando cambia el estado de un pedido.

* **Responsabilidades**:

  * Recuperar pedidos que están en estado pendiente o en preparación.
  * Permitir a los cocineros actualizar el estado de un pedido.
  * Notificar en tiempo real mediante WebSocket el cambio de estado de los pedidos.

* **Ruta Gateway**: `/api/kitchen/**`

### WebSocket

* **Endpoint STOMP**: `/ws-kitchen`

* **Topic de suscripción**: `/topic/order-status`

* **Protocolo**: SockJS/STOMP

* **Destino de envío desde backend**: Mensajes enviados desde el backend a `/topic/order-status` cuando cambia el estado de un pedido.

* **Nota**: Este microservicio no posee entidad persistente propia; trabaja directamente sobre los pedidos ya creados y almacenados en `order-service`, consumiendo sus datos vía Feign o DTOs internos.

### Endpoints

| Método | Ruta                             | Descripción                                                    | Requiere Autenticación |
|--------|----------------------------------|----------------------------------------------------------------|--------------------------|
| GET    | /api/kitchen/orders              | Obtener todos los pedidos pendientes para cocina               | Sí (COOK)                |
| PUT    | /api/kitchen/orders/{id}/status  | Actualizar el estado de un pedido desde cocina                 | Sí (COOK)                |

---

#### WebSocket en cocina

El microservicio publica actualizaciones del estado de los pedidos en tiempo real mediante WebSocket para que tanto clientes como cocineros estén sincronizados.

- **Endpoint STOMP**: `/ws-kitchen`
- **Canal de suscripción**: `/topic/order-status`
- **Protocolo**: SockJS + STOMP
- **Payload de ejemplo**:

```json
{
  "id": 13,
  "status": "IN_PREPARATION"
}
```

### Uso de Feign y propagación de tokens

microservice-kitchen se comunica con microservice-order usando Feign Client para:

- Obtener los pedidos asignados a cocina.
- Actualizar su estado.

Para que estas llamadas funcionen correctamente, es necesario propagar el token JWT entre microservicios. Esto se consigue mediante la configuración de un interceptor que añade automáticamente el encabezado Authorization a todas las peticiones Feign.

---

<div style="page-break-after: always;"></div>