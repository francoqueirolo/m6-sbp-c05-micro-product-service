# Product Service

## 📋 Descripción
Microservicio para la gestión de productos desarrollado con Spring Boot 3.5.6. Este servicio forma parte de un ecosistema de microservicios y utiliza PostgreSQL como base de datos.

## 🛠️ Tecnologías
- **Java 17**
- **Spring Boot 3.5.6**
- **Spring Data JPA**
- **PostgreSQL 15**
- **Flyway** para migraciones de base de datos
- **Maven** como gestor de dependencias
- **Docker** para contenerización

## 🏗️ Estructura del Proyecto
```
product-service/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   │       └── application.yml
├── docker/
├── docs/
├── Dockerfile
├── Jenkinsfile
└── pom.xml
```

## ⚙️ Configuración

### Variables de Entorno
- `SPRING_PROFILES_ACTIVE`: Perfil activo (local, dev, prod)
- `SERVER_PORT`: Puerto de la aplicación (por defecto: 8082)
- `SPRING_DATASOURCE_URL`: URL de conexión a PostgreSQL
- `SPRING_DATASOURCE_USERNAME`: Usuario de la base de datos
- `SPRING_DATASOURCE_PASSWORD`: Contraseña de la base de datos

### Perfiles
- **local**: Configuración para desarrollo local
- **dev**: Configuración para entorno de desarrollo
- **prod**: Configuración para producción

## 🚀 Despliegue Local

### Requisitos Previos
- Java 17
- Maven 3.6+
- Docker y Docker Compose
- PostgreSQL 15

### Pasos para Ejecutar

1. **Clonar el repositorio**
   ```
   git clone [URL_DEL_REPOSITORIO]
   cd product-service
   ```

1. **Iniciar la base de datos con Docker**
   ```
   docker-compose up -d postgres-product-dev
   ```

2. **Compilar y ejecutar la aplicación**
   ```
   mvn clean install
   mvn spring-boot:run
   ```

3. **Acceder a la aplicación**
    - API: http://localhost:8082
    - Health Check: http://localhost:8082/actuator/health

## 🐳 Despliegue con Docker

### Construir la imagen
```
docker build -t product-service:latest .
```

### Ejecutar con Docker Compose
```
docker-compose up -d
```

## 📊 Monitoreo
La aplicación incluye Spring Boot Actuator para monitoreo:
- Health: `GET /actuator/health`
- Info: `GET /actuator/info`
- Metrics: `GET /actuator/metrics`

## 📄 Licencia
Este proyecto está bajo la licencia [MIT](LICENSE).