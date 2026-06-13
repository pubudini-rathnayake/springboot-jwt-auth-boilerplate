# 🔐 Spring Boot JWT Auth Boilerplate

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-6DB33F?logo=springboot)
![Java](https://img.shields.io/badge/Java-17-orange?logo=java)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6.2.4-6DB33F?logo=springsecurity)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?logo=mysql)
![JWT](https://img.shields.io/badge/JWT-Auth-black?logo=jsonwebtokens)
![License](https://img.shields.io/badge/License-MIT-green)
![Status](https://img.shields.io/badge/Status-Active-brightgreen)

A clean, production-ready **JWT Authentication Boilerplate** built with Spring Boot 3, Spring Security 6, and MySQL. Includes access tokens, refresh tokens, and role-based access control — ready to plug into any project.

---

## ✨ Features

| Feature | Details |
|---|---|
| 🔑 Authentication | Register, Login, Logout |
| 🔄 JWT Refresh Token | Silent token renewal without re-login |
| 🛡️ Role-Based Access | USER and ADMIN roles with method-level security |
| 🗄️ Database | MySQL with Spring Data JPA |
| ✅ Validation | Input validation with meaningful error messages |
| 📖 API Docs | Swagger UI via SpringDoc OpenAPI |
| 🔒 Password Security | BCrypt password hashing |
| ⚡ Stateless | No session state — fully REST-compliant |

---

## 🏗️ Architecture

```
Client Request
      │
      ▼
JwtAuthFilter (validates Bearer token)
      │
      ▼
SecurityFilterChain
      │
      ├── /api/auth/**  →  Public (no token needed)
      ├── /api/admin/** →  ADMIN role only
      └── /api/**       →  Authenticated users only
            │
            ▼
      AuthController / TestController
            │
            ▼
      AuthService + RefreshTokenService
            │
            ▼
      UserRepository + RefreshTokenRepository
            │
            ▼
         MySQL Database
```

---

## 📁 Project Structure

```
src/
└── main/
    ├── java/com/pubudini/jwtauthboilerplate/
    │   ├── config/
    │   │   └── SecurityConfig.java         # Spring Security configuration
    │   ├── controller/
    │   │   ├── AuthController.java         # Auth endpoints
    │   │   └── TestController.java         # Protected test endpoints
    │   ├── dto/
    │   │   ├── request/
    │   │   │   ├── LoginRequest.java
    │   │   │   ├── RegisterRequest.java
    │   │   │   └── RefreshTokenRequest.java
    │   │   └── response/
    │   │       ├── AuthResponse.java
    │   │       └── MessageResponse.java
    │   ├── entity/
    │   │   ├── User.java                   # User entity
    │   │   └── RefreshToken.java           # Refresh token entity
    │   ├── enums/
    │   │   └── Role.java                   # USER, ADMIN roles
    │   ├── exception/
    │   │   ├── GlobalExceptionHandler.java
    │   │   └── TokenException.java
    │   ├── repository/
    │   │   ├── UserRepository.java
    │   │   └── RefreshTokenRepository.java
    │   ├── security/
    │   │   ├── jwt/
    │   │   │   ├── JwtService.java         # Token generation & validation
    │   │   │   └── JwtAuthFilter.java      # Request filter
    │   │   └── UserDetailsServiceImpl.java
    │   ├── service/
    │   │   ├── AuthService.java            # Business logic
    │   │   └── RefreshTokenService.java
    │   └── JwtAuthBoilerplateApplication.java
    └── resources/
        └── application.properties
```

---

## 🚀 Quick Start

### Prerequisites
- Java 17+
- MySQL 8.0+
- Maven 3.8+

### 1. Clone the repository
```bash
git clone https://github.com/pubudini-rathnayake/springboot-jwt-auth-boilerplate.git
cd springboot-jwt-auth-boilerplate
```

### 2. Create the MySQL database
```sql
CREATE DATABASE jwt_auth_db;
```

### 3. Configure environment
Open `src/main/resources/application.properties` and update:
```properties
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
```

### 4. Run the application
```bash
./mvnw spring-boot:run
```

Or run `JwtAuthBoilerplateApplication.java` directly in IntelliJ.

### 5. Access Swagger UI
```
http://localhost:8080/swagger-ui/index.html
```

---

## 🔌 API Endpoints

### Auth Endpoints (Public)

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Login and receive tokens |
| POST | `/api/auth/refresh` | Get a new access token |
| POST | `/api/auth/logout` | Logout and invalidate refresh token |

### Protected Endpoints

| Method | Endpoint | Role Required | Description |
|---|---|---|---|
| GET | `/api/user/profile` | USER | User profile |
| GET | `/api/admin/dashboard` | ADMIN | Admin dashboard |

---

## 📋 Request & Response Examples

### Register
```json
POST /api/auth/register
{
  "fullName": "Pubudini Rathnayake",
  "email": "pubudini@example.com",
  "password": "password123"
}
```

### Response
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiJ9...",
  "refreshToken": "550e8400-e29b-41d4-a716-446655440000",
  "email": "pubudini@example.com",
  "fullName": "Pubudini Rathnayake",
  "role": "USER"
}
```

### Login
```json
POST /api/auth/login
{
  "email": "pubudini@example.com",
  "password": "password123"
}
```

### Refresh Token
```json
POST /api/auth/refresh
{
  "refreshToken": "550e8400-e29b-41d4-a716-446655440000"
}
```

### Using the Access Token
```
GET /api/user/profile
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

---

## ⚙️ Configuration

| Property | Default | Description |
|---|---|---|
| `app.jwt.secret` | (256-bit key) | JWT signing secret |
| `app.jwt.access-token-expiration` | `900000` | Access token expiry (15 mins) |
| `app.jwt.refresh-token-expiration` | `604800000` | Refresh token expiry (7 days) |
| `server.port` | `8080` | Server port |

---

## 🗺️ Roadmap

- [ ] Email verification on registration
- [ ] Forgot password / reset password flow
- [ ] OAuth2 social login (Google)
- [ ] Docker support
- [ ] Unit and integration tests

---

## 🛠️ Built With

- [Spring Boot 3.2.5](https://spring.io/projects/spring-boot) — Application framework
- [Spring Security 6](https://spring.io/projects/spring-security) — Authentication & authorization
- [JJWT 0.11.5](https://github.com/jwtk/jjwt) — JWT library
- [MySQL](https://www.mysql.com/) — Database
- [Lombok](https://projectlombok.org/) — Boilerplate reduction
- [SpringDoc OpenAPI](https://springdoc.org/) — Swagger UI

---

## 👩‍💻 Author

**Pubudini Rathnayake**
- GitHub: [@pubudini-rathnayake](https://github.com/pubudini-rathnayake)
- LinkedIn: [linkedin.com/in/pubudini-rathnayake](https://linkedin.com/in/pubudini-rathnayake)

---

## 📄 License

MIT License — see [LICENSE](LICENSE) for details.

---

⭐ If this boilerplate saved you time, consider giving it a star!