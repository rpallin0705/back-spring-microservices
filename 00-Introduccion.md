## Índice

* [00 - Introducción a Kizuna Gourmet (TFG)](#00---introducción-a-kizuna-gourmet-tfg)
* [01 - Stack Tecnológico del Proyecto](#01---stack-tecnológico-del-proyecto)

  * [Backend](#backend)

    * [Tecnologías clave](#tecnologías-clave)
    * [Detalles adicionales](#detalles-adicionales)

      * [Descubrimiento de servicios](#descubrimiento-de-servicios)
      * [Configuración externa](#configuración-externa)
      * [Seguridad](#seguridad)
      * [Comunicación entre servicios](#comunicación-entre-servicios)
      * [Persistencia](#persistencia)
      * [WebSocket](#websocket)
      * [Despliegue](#despliegue)
  * [🐋 Infraestructura y despliegue con Docker](#-infraestructura-y-despliegue-con-docker)

    * [Estructura General del `docker-compose.yml`](#estructura-general-del-docker-composeyml)
    * [Base de datos unificada](#base-de-datos-unificada)
    * [Dockerfile común de microservicios de entidad](#dockerfile-común-de-microservicios-de-entidad)
    * [Script de espera `wait-for.sh`](#script-de-espera-wait-forsh)
    * [Script especial de espera para Gateway](#script-especial-de-espera-para-gateway)
    * [Comando de arranque global](#comando-de-arranque-global)
  * [Stack Tecnológico - Frontend](#stack-tecnológico---frontend)

    * [Framework y herramientas principales](#framework-y-herramientas-principales)
    * [Persistencia de sesión](#persistencia-de-sesión)
    * [Estructura de carpetas destacadas](#estructura-de-carpetas-destacadas)
    * [Despliegue y entorno](#despliegue-y-entorno)
* [02 - Estructura del Frontend](#02---estructura-del-frontend)

  * [Estructura de carpetas](#estructura-de-carpetas)
  * [Conexión con el backend](#conexión-con-el-backend)

    * [Configuración global de Axios](#configuración-global-de-axios)
    * [Ejemplo de uso: `authApi`](#ejemplo-de-uso-authapi)
  * [Comunicación en tiempo real (WebSocket)](#comunicación-en-tiempo-real-websocket)

    * [Conexión WebSocket modular](#conexión-websocket-modular)
    * [Composables reactivos](#composables-reactivos)
  * [Sistema de rutas y control de acceso](#sistema-de-rutas-y-control-de-acceso)

    * [Estructura del router](#estructura-del-router)
    * [Sistema de guardia de navegación](#sistema-de-guardia-de-navegación)
  * [Sistema de diseño con Vuetify](#sistema-de-diseño-con-vuetify)

    * [Temas personalizados](#temas-personalizados)
    * [Iconos](#iconos)
  * [Gestión de estado con Pinia](#gestión-de-estado-con-pinia)

    * [Configuración global](#configuración-global)
    * [Ejemplo de store: `useProductStore`](#ejemplo-de-store-useproductstore)
* [03 - Estructura de Microservicios](#03---estructura-de-microservicios)

  * [Microservicios principales](#microservicios-principales)

    * [microservice-config](#microservice-config)
    * [microservice-eureka](#microservice-eureka)
    * [microservice-gateway](#microservice-gateway)
  * [microservice-product](#microservice-product)

    * [Modelos principales](#modelos-principales)
    * [Endpoints - microservice-product](#endpoints---microservice-product)
  * [microservice-auth](#microservice-auth)

    * [Modelos principales](#modelos-principales-1)
    * [Endpoints - microservice-auth](#endpoints---microservice-auth)
  * [microservice-user](#microservice-user)

    * [Modelos principales](#modelos-principales-2)
    * [Endpoints - User](#endpoints---user)
    * [Endpoints - Address](#endpoints---address)
  * [microservice-order](#microservice-order)

    * [Modelos principales](#modelos-principales-3)
    * [Endpoints](#endpoints)
    * [Ejemplo de respuesta enriquecida (OrderDTO)](#ejemplo-de-respuesta-enriquecida-orderdto)
    * [Construcción de OrderDTO enriquecido](#construcción-de-orderdto-enriquecido)
  * [microservice-kitchen](#microservice-kitchen)

    * [WebSocket](#websocket)
    * [Endpoints](#endpoints-1)
    * [WebSocket en cocina](#websocket-en-cocina)
    * [Uso de Feign y propagación de tokens](#uso-de-feign-y-propagación-de-tokens)
* [04 - Estructura de base de datos](#04---estructura-de-base-de-datos)

  * [Estrategia general](#estrategia-general)
  * [Descripción por microservicio](#descripción-por-microservicio)

    * [msvc-product](#msvc-product)
    * [msvc-user](#msvc-user)
    * [msvc-auth](#msvc-auth)
    * [msvc-order](#msvc-order)
  * [Relaciones entre microservicios (a nivel lógico)](#relaciones-entre-microservicios-a-nivel-lógico)
  * [Diagrama UML](#diagrama-uml)

    * [Diagrama UML sin cruzar](#diagrama-uml-sin-cruzar)
    * [Diagrama UML cruzado](#diagrama-uml-cruzado)
  * [Consideraciones](#consideraciones)
* [05 - Puesta en marcha del sistema](#05---puesta-en-marcha-del-sistema)

  * [Backend con Docker Compose](#backend-con-docker-compose)
  * [Tiempo de arranque y consumo de recursos](#tiempo-de-arranque-y-consumo-de-recursos)
  * [Verificación de estado](#verificación-de-estado)
  * [Contraseñas y configuración](#contraseñas-y-configuración)
  * [Frontend con Vue](#frontend-con-vue)
* [06 - Conclusiones](#06---conclusiones)

  * [Arquitectura de microservicios](#arquitectura-de-microservicios)
  * [Frontend con Vue y Vuetify](#frontend-con-vue-y-vuetify)
  * [Posibles mejoras futuras](#posibles-mejoras-futuras)

<div style="page-break-after: always;"></div>

# 00 - Introducción a Kizuna Gourmet (TFG)

Kizuna Gourmet es una plataforma integral de gestión de pedidos gastronómicos, diseñada como Trabajo de Fin de Grado (TFG). Está orientada a digitalizar la experiencia de un restaurante japonés mediante una arquitectura moderna basada en microservicios para el backend y aplicaciones frontend para clientes y administradores.

El sistema permite a los usuarios realizar pedidos desde la web, mientras que el personal de cocina gestiona su preparación en tiempo real. Incluye funciones de autenticación, roles, consulta de menús y productos, actualizaciones en vivo con WebSocket y despliegue orquestado con Docker.

---

## Objetivos del TFG

* Diseñar un sistema distribuido basado en microservicios.
* Implementar una arquitectura completa de backend con Spring Boot, Eureka y Feign.
* Integrar un frontend moderno con Jetpack Compose y Vue 3.
* Facilitar la comunicación entre servicios de manera robusta y escalable.
* Ofrecer una experiencia fluida mediante WebSocket para actualizaciones en tiempo real.
* Documentar de forma profesional y estructurada todo el sistema.

---

## Tecnologías principales

### Backend (Spring Boot)

* Spring Boot 3.4.5
* Spring Cloud (Eureka, Config, Gateway)
* Feign Client para comunicación entre servicios
* WebSocket con STOMP
* JPA/Hibernate con MySQL
* Seguridad con JWT y Spring Security (en auth-service)

### Frontend Web (Vue 3 + Vuetify)

* Vue 3 y Vue Router 4
* Vuetify 3.8.7 para el diseño visual
* Pinia para gestión de estado global
* Axios para consumo de APIs REST
* STOMP y SockJS para WebSocket
* WebFontLoader y Roboto Fontface para la tipografía
* Soporte completo para TypeScript

### Frontend Android (Jetpack Compose)

* Kotlin
* Android Jetpack
* Firebase para autenticación
* Retrofit + StateFlow para peticiones y estado
* Coil para carga de imágenes

### Infraestructura

* Docker Compose para despliegue orquestado
* Ngrok para exposición remota del backend
* Banana Pi M1 como servidor de producción local

---

## Módulos backend

* `microservice-config`: Configuración centralizada.
* `microservice-eureka`: Registro de servicios.
* `microservice-gateway`: Enrutamiento dinámico y entrada única.
* `microservice-product`: Productos, categorías y menús.
* `microservice-order`: Pedidos y su historial.
* `microservice-kitchen`: Preparación de pedidos en cocina.
* `microservice-user`: Usuarios y sus direcciones.
* `microservice-auth`: Autenticación y roles con JWT.

---

## Flujo general del sistema

1. **El cliente realiza un pedido** desde la app Android o desde el panel web.
2. **order-service** guarda el pedido y lo comunica a **kitchen-service**.
3. La cocina actualiza el estado del pedido (“en cocina”, “listo”, etc.).
4. **WebSocket** informa en tiempo real al cliente sobre el estado del pedido.
5. El backend consulta información de productos y menús usando **product-service**.
6. La autenticación y control de acceso se gestionan desde **auth-service**.

---

<div style="page-break-after: always;"></div>