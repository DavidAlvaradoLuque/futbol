# Fútbol Web - Spring Boot + MySQL

Aplicación web de gestión de fútbol colombiano con Spring Boot 3, JPA, Thymeleaf y MySQL.

## Entidades

- **Asociación** – federaciones (FCF, FIFA, CONMEBOL)
- **Club** – equipos (Millonarios, Nacional, Santa Fe, Cali)
- **Entrenador** – técnicos de cada club
- **Jugador** – jugadores por club
- **Competición** – torneos (Liga BetPlay, Copa Libertadores…)

## Stack

- Java 17
- Spring Boot 3.2.5
- Spring Data JPA + Hibernate
- MySQL 8
- Thymeleaf
- Maven

## Variables de entorno (Render)

| Variable | Descripción |
|---|---|
| `MYSQL_URL` | JDBC URL completa de la base de datos MySQL |
| `MYSQL_USER` | Usuario de MySQL |
| `MYSQL_PASSWORD` | Contraseña de MySQL |
| `PORT` | Puerto (Render lo inyecta automáticamente) |

## Ejecución local

```bash
# 1. Crear base de datos
mysql -u root -p -e "CREATE DATABASE futboldb;"

# 2. Ajustar credenciales en application.properties

# 3. Ejecutar
mvn spring-boot:run
```
