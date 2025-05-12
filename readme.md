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

### Dependencias

* Spring Web Web
* Spring Data JPA SQL
* MySQL Driver SQL
* MySQL JDBC driver.
* Eureka Discovery Client Spring Cloud Discovery
* Spring Boot Actuator Ops

### Productos-service-db

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
## Microservicios Pedidos

### Dependencias

* Spring Web Web
* Spring Data JPA SQL
* MySQL Driver SQL
* MySQL JDBC driver.
* Eureka Discovery Client Spring Cloud Discovery
* Spring Boot Actuator Ops

### Pedidos-service db

```sql
PEDIDO (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  cliente_id BIGINT, -- puede venir del token o de cliente-service
  fecha DATETIME,
  tipo ENUM('LOCAL', 'DOMICILIO'),
  estado ENUM('PENDIENTE', 'EN_PREPARACION', 'EN_CAMINO', 'ENTREGADO'),
  direccion_entrega VARCHAR(255), -- null si es pedido local
  repartidor_id BIGINT -- null hasta que se asigne
);

DETALLE_PEDIDO (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  pedido_id BIGINT,
  producto_id BIGINT,
  nombre_producto VARCHAR(150), -- snapshot del producto
  cantidad INT,
  precio_unitario DECIMAL(10,2),
  FOREIGN KEY (pedido_id) REFERENCES PEDIDO(id)
);
```

