## 06 - Conclusiones

La arquitectura basada en microservicios ha demostrado ser una opción poderosa y escalable para el desarrollo de aplicaciones modernas como **Kizuna Gourmet**. Esta forma de estructurar el backend permite una modularidad muy elevada, donde cada funcionalidad clave (usuarios, pedidos, productos, cocina, autenticación) se encapsula en un microservicio autónomo, con su propia base de datos, lógica de negocio y ciclo de vida independiente.

Este enfoque modular permite que **cada microservicio sea desarrollado, probado y desplegado por separado**, lo que facilita el trabajo en equipo. En entornos reales, **es habitual que cada microservicio esté gestionado por un equipo o programador específico**, lo que permite una escalabilidad organizativa además de técnica.

Por otro lado, el frontend desarrollado en **Vue 3** ha sido una elección muy acertada para construir una **Single Page Application (SPA)** reactiva, modular y mantenible. La integración con **Vuetify** ha simplificado enormemente el diseño visual de la aplicación, gracias a su sistema de temas, layouts preconfigurados e integración con Material Design 3.

También se ha demostrado la utilidad de herramientas como **Pinia** para el manejo del estado global de forma sencilla y escalable, así como el uso de **WebSocket (STOMP + SockJS)** para conseguir actualizaciones en tiempo real entre cocina y clientes.

---

### Posibles mejoras y ampliaciones futuras

Durante el desarrollo se han identificado distintas oportunidades para seguir mejorando la aplicación:

* **Aplicación móvil nativa**: Se podría haber creado una app para Android/iOS para los clientes o para el uso exclusivo de los repartidores.
* **Microservicio para repartidores**: Dedicado al seguimiento de envíos, rutas y coordinación de entregas.
* **Mejoras de seguridad**: Se podrían añadir validaciones más estrictas, encriptación avanzada, rotación de tokens o sistemas de auditoría.
* **Excepciones personalizadas**: Implementar un sistema uniforme de errores controlados y respuestas claras al cliente.
* **Pruebas automatizadas**: Añadir tests unitarios y de integración para asegurar la robustez de cada microservicio.
* **Observabilidad y métricas**: Incorporar herramientas como Prometheus + Grafana para la monitorización del sistema.

---

Este TFG ha permitido poner en práctica una arquitectura moderna, profesional y orientada a la escalabilidad, sentando las bases para futuros proyectos complejos con microservicios y SPAs reales.