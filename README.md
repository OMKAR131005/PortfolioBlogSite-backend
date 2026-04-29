# 📝 Portfolio Blog Site — Backend

A production-ready REST API backend for a personal portfolio and blog platform, built with **Spring Boot 3** and **Java 17**. Features JWT authentication, role-based access control, rate limiting, and full Swagger documentation.

---

## 🚀 Tech Stack

| Layer | Technology |
|---|---|
| Framework | Spring Boot 3.2.5 |
| Language | Java 17 |
| Database | MySQL |
| ORM | Spring Data JPA / Hibernate |
| Security | Spring Security + JWT (JJWT 0.11.5) |
| Rate Limiting | Bucket4j 8.10.1 |
| API Docs | SpringDoc OpenAPI (Swagger UI) |
| Validation | Spring Boot Validation |
| Build Tool | Maven |
| Containerisation | Docker (multi-stage build) |
| Deployment | Render |

---

## ✨ Features

- **JWT Authentication** — Stateless login and registration with access tokens
- **Role-Based Access Control** — Protect endpoints by user roles (e.g. ADMIN, USER)
- **Blog Management** — CRUD operations for blog posts
- **Rate Limiting** — Per-IP request throttling using Bucket4j to prevent abuse
- **Input Validation** — Bean Validation annotations on all request DTOs
- **Swagger UI** — Interactive API documentation available at `/swagger-ui/index.html`
- **Dockerised** — Multi-stage Dockerfile for lean, production-ready images
- **CORS Configured** — Ready to integrate with a React/Next.js frontend

---

## 📁 Project Structure

```
src/
└── main/
    └── java/com/omkar/
        ├── config/         # Security, CORS, JWT, OpenAPI config
        ├── controller/     # REST controllers
        ├── dto/            # Request & response DTOs
        ├── entity/         # JPA entities
        ├── exception/      # Global exception handling
        ├── repository/     # Spring Data JPA repositories
        └── service/        # Business logic
```

---

## ⚙️ Prerequisites

- Java 17+
- Maven 3.9+
- MySQL 8+
- Docker (optional, for containerised setup)

---

## 🛠️ Local Setup

### 1. Clone the repository

```bash
git clone https://github.com/OMKAR131005/PortfolioBlogSite-backend.git
cd PortfolioBlogSite-backend
```

### 2. Configure environment

Create an `application.properties` (or `application-dev.properties`) under `src/main/resources/`:

```properties
# Server
server.port=8080

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/portfolio_db
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update

# JWT
jwt.secret=your_jwt_secret_key
jwt.expiration=86400000
```

### 3. Run the application

```bash
./mvnw spring-boot:run
```

The server will start at `http://localhost:8080`.

---

## 🐳 Docker

### Build and run with Docker

```bash
# Build the image
docker build -t portfolio-backend .

# Run the container
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://<host>:3306/portfolio_db \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=secret \
  -e JWT_SECRET=your_secret \
  portfolio-backend
```

> The Dockerfile uses a **multi-stage build** — Maven compiles the JAR in the build stage, and the final image is a minimal `eclipse-temurin:17-jre-alpine` image (~85 MB).

---

## 📖 API Documentation

Once the server is running, visit:

```
http://localhost:8080/swagger-ui/index.html
```

All endpoints are documented with request/response schemas, authentication requirements, and example payloads.

---

## 🔑 Authentication Flow

```
POST /api/auth/register   →  Register a new user
POST /api/auth/login      →  Returns JWT access token
```

Pass the token in subsequent requests:

```
Authorization: Bearer <your_jwt_token>
```

---

## 🌐 Deployment (Render)

This project is configured for one-click deployment on [Render](https://render.com).

1. Push to GitHub.
2. Create a new **Web Service** on Render and connect the repository.
3. Set the **Start Command** to:
   ```
   java -jar app.jar --spring.profiles.active=prod
   ```
4. Add the required environment variables (`SPRING_DATASOURCE_URL`, `JWT_SECRET`, etc.) in the Render dashboard.
5. Render automatically sets the `PORT` environment variable — the app respects it via `server.port=${PORT:8080}`.

---

## 📜 License

This project is open-source and available under the [MIT License](LICENSE).

---

> Built by [Omkar](https://github.com/OMKAR131005)
