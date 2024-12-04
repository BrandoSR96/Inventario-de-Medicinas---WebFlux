# Inventario-de-Medicinas---WebFlux
Proyecto en desarrollo: API de inventario de medicinas con arquitectura reactiva (Spring WebFlux + R2DBC) y soporte para reportes CSV. Ideal para entornos de alto rendimiento. 🚀 En desarrollo. Contribuciones y sugerencias son bienvenidas. 😊

# Inventario de Medicinas Reactivo

Este proyecto es una API reactiva para gestionar un inventario de medicinas. Está desarrollado con **Spring WebFlux** y utiliza una base de datos relacional accesible a través de **R2DBC**. Permite realizar operaciones CRUD y generar reportes en formato CSV.

> **Estado del proyecto:**  
> 🚧 **En desarrollo**: Aún falta validar algunas funcionalidades, controlar errores y realizar pruebas más exhaustivas.

## **Características principales**
- **Gestión de inventario:**
  - Listar todas las medicinas.
  - Crear nuevas medicinas.
  - Eliminar medicinas específicas o todo el inventario.
- **Generación de reportes:**
  - Genera un archivo CSV con los datos del inventario.
- Arquitectura **reactiva** para soportar aplicaciones de alto rendimiento.

## **Tecnologías utilizadas**
- **Java** (versión 17+)
- **Spring Boot** (WebFlux, Data R2DBC)
- **R2DBC** (Reactive Relational Database Connectivity)
- **MySQL** como base de datos relacional
- **Lombok** para reducir el código repetitivo

## **Requisitos previos**
1. Tener instalado **Java 17+**.
2. Tener configurado **MySQL**.
3. Configurar las credenciales de acceso a la base de datos en el archivo `application.properties`.

## **Estructura del proyecto**
- **Model:** Define las entidades utilizadas en la base de datos.
- **Repository:** Contiene la capa de acceso a datos utilizando `ReactiveCrudRepository`.
- **Service:** Implementa la lógica de negocio.
- **Controller:** Gestiona las solicitudes y respuestas HTTP.
- **Exception:** Define el manejo de errores personalizados.

## **Configuración**
En el archivo `src/main/resources/application.properties`, asegúrate de ajustar los valores según tu configuración:
```properties
spring.application.name=inventario_santiago
spring.r2dbc.url=r2dbc:mysql://localhost:3306/inventario_santiago
spring.r2dbc.username=root
spring.r2dbc.password=123456
spring.r2dbc.pool.initial-size=5
spring.r2dbc.pool.max-size=20
spring.main.web-application-type=reactive
```
## **Ejemplo de uso**
Endpoints disponibles

**GET /api/medicina :** Obtiene la lista de todas las medicinas.

**POST /api/medicina :** Crea una nueva medicina.

Body (JSON):
```
{
  "name": "Paracetamol",
  "description": "Analgésico y antipirético",
  "quantity": 100,
  "expirationDate": "2025-12-31",
  "createdAt": "2024-12-04T12:00:00"
}
```
**DELETE /api/medicina/{id} :** Elimina una medicina por su ID.

**DELETE /api/medicina/eliminarMedicinas :** Elimina todas las medicinas del inventario.

**GET /api/medicina/reporte-csv :** Genera y descarga un archivo CSV con los datos del inventario.

## **Pendientes del proyecto**
1. Validar las entradas de usuario (por ejemplo, campos obligatorios, formatos de fecha, etc.).
2. Implementar un manejo de excepciones más robusto.
3. Realizar pruebas unitarias y de integración.
4. Documentar la API utilizando **Swagger** u otra herramienta.
5. Mejorar la gestión de errores para garantizar una experiencia más clara.

## **Ejecución**
1. Clona el repositorio:
```
git clone https://github.com/BrandoSR96/Inventario-de-Medicinas-WebFlux.git
```
2. Navega al directorio del proyecto:
```
cd inventario-medicinas-reactivo
```
3. Compila y ejecuta el proyecto:
```
./mvnw spring-boot:run
```
4. Accede a la API en: http://localhost:8080/api/medicina

## **Contribuciones**
Si deseas contribuir a este proyecto, ¡siéntete libre de realizar un fork o abrir un issue en el repositorio!
Toda ayuda será bienvenida para mejorar y finalizar las funcionalidades.
