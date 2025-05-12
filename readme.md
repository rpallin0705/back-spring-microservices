## Dependecias spring gateway
- Gateway Spring Cloud Routing
- Eureka Discovery Client Spring Cloud Discovery
- Config Client Spring Cloud Config
- Spring Boot Actuator Ops

## [MicroServicio Eureka](http://localhost:8761)

### Dependecias
- Eureka Server Spring Cloud Discovery
- Config Client Spring Cloud Config
- Spring Boot Actuator Ops

### Dependecias
- Eureka client Spring Cloud Discovery
- Config Client Spring Cloud Config
- Spring Boot Actuator Ops
## Microservicio Productos

Spring Web Web
* Spring Data JPA SQL
* MySQL Driver SQL
* MySQL JDBC driver.
* Eureka Discovery Client Spring Cloud Discovery
* Spring Boot Actuator Ops

Productos-service-db

```sql
PRODUCTO (
  id BIGINT PRIMARY KEY,
  nombre VARCHAR(100),
  descripcion TEXT,
  precio DECIMAL(10,2),
  imagen_url VARCHAR(255),
  disponible BOOLEAN
);

CATEGORIA (
  id BIGINT PRIMARY KEY,
  nombre VARCHAR(100)
);

MENU (
  id BIGINT PRIMARY KEY,
  nombre VARCHAR(100),
  descripcion TEXT,
  precio_total DECIMAL(10,2),
  activo BOOLEAN
);

MENU_PRODUCTO (
  id BIGINT PRIMARY KEY,
  menu_id BIGINT,
  producto_id BIGINT,
  cantidad INT,
  FOREIGN KEY (menu_id) REFERENCES MENU(id),
  FOREIGN KEY (producto_id) REFERENCES PRODUCTO(id)
);
```