## 05 - Puesta en marcha del sistema

Este documento describe cómo arrancar completamente el sistema tanto a nivel de backend como de frontend.

---

### Backend con Docker Compose

El backend está compuesto por múltiples microservicios desplegados en contenedores Docker orquestados mediante `docker-compose`. Desde la raíz del proyecto backend, el proceso es:

```bash
docker compose build
```

```bash
docker compose up
```

Esto compilará e iniciará todos los servicios definidos, incluyendo:

* `mysql`
* `config-server`
* `eureka-server`
* `auth-service`
* `product-service`
* `user-service`
* `order-service`
* `kitchen-service`
* `gateway`

### Tiempo de arranque y consumo de recursos

> Se requieren bastantes recursos, ya que se levantan 9 contenedores.

El arranque completo puede tardar varios segundos o incluso minutos dependiendo del entorno. El último microservicio en estar disponible es el `gateway`, ya que depende de que todos los servicios de negocio estén listos.

### Verificación de estado

Para comprobar que todo ha arrancado correctamente, se pueden observar los logs del contenedor `gateway`:

```bash
docker logs -f gateway
```

Una vez que el `gateway` esté sirviendo en el puerto `8080`, el sistema estará operativo.

### Contraseñas y configuración

> Por simplicidad, las contraseñas de las bases de datos de desarrollo (ej. `MYSQL_ROOT_PASSWORD: 12345`) están en texto plano dentro del `docker-compose.yml`, sin uso de variables de entorno. En entornos reales **esto no es seguro** y deben utilizarse archivos `.env` o sistemas de gestión de secretos para proteger credenciales y datos sensibles.

---

### Frontend con Vue

El frontend está implementado en Vue 3 + Vuetify + TypeScript y se puede lanzar en modo desarrollo con:

```bash
npm install
npm run serve
```

Esto iniciará el servidor en `http://localhost:5173` (o el puerto que esté configurado en `vue.config.js`).

Para iniciar sesión en la aplicación los usuarios son:

- cliente1@gmail.com 
- cook1@gmail.com
- admin1@gmail.com

Todos con contraseña: 12345

> En esta versión, no se ha realizado despliegue en producción ni empaquetado con Docker. Para pruebas y desarrollo, `npm run serve` es suficiente.

<div style="page-break-after: always;"></div>