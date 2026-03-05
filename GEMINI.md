# TaskManager Backend - Project Documentation

This document serves as a persistent knowledge base for the TaskManager Backend project, documenting the architectural decisions, entity structures, and API configurations.

## Architecture
The project follows the **MVC (Model-View-Controller)** pattern using **Spring Boot 3.4.x** (configured for Java 21).

### Tech Stack
- **Framework:** Spring Boot (Web, Data JPA, Security)
- **Database:** H2 (In-memory for development)
- **Utilities:** Lombok, Jakarta Persistence (JPA), JJWT (v0.12.6)

## Database Configuration (`application.properties`)
- **URL:** `jdbc:h2:mem:taskdb`
- **User/Password:** `sa` / (empty)
- **H2 Console:** Enabled at `/h2-console`
- **Hibernate DDL:** `create-drop` (automatic table creation)
- **Seed Scripts:** `user.sql` and `tasks.sql` are loaded on startup.

## Entities

### User (`com.helalferrari.taskmanager.model.User`)
| Attribute | Type | Constraints |
| :--- | :--- | :--- |
| `id` | UUID | Primary Key |
| `email` | String | Unique, Not Null |
| `password` | String | Not Null (BCrypt encoded) |

### Task (`com.helalferrari.taskmanager.model.Task`)
| Attribute | Type | Constraints |
| :--- | :--- | :--- |
| `id` | UUID | Primary Key |
| `user` | User | Foreign Key (`user_id`), Not Null |
| `title` | String | Not Null |
| `completed` | boolean | Not Null |
| `deleted` | boolean | Not Null |
| `createDate`| Date | Auto-generated on persist |
| `modifyDate`| Date | Auto-updated on update |

## API Endpoints

### Authentication
- `POST /api/auth/login`: Authenticates a user and returns a JWT token + user info.

### Tasks (Protected by JWT)
- `GET /api/tasks`: Lists all tasks in the system.
- `GET /api/tasks/user/{userId}`: Lists all tasks for a specific user.
- `POST /api/tasks`: Creates a new task for a specific user.
- `PUT /api/tasks/{id}`: Updates task title, completed status, or deleted status (Requires ownership).
- `PATCH /api/tasks/{id}/complete`: Marks a task as completed (Any authenticated user).
- `PATCH /api/tasks/{id}/uncomplete`: Marks a task as uncompleted (Any authenticated user).

### Users
- `POST /api/users/register`: Creates a new user and returns a JWT token for automatic login (Public).
- `GET /api/users/{id}`: Retrieves user details by ID (Protected).
- `PUT /api/users/{id}`: Updates user email and/or password (Protected).

## Evolutions & Recent Changes
- **Auto-login on Register (2026-03-04):** The registration API (`POST /api/users/register`) was updated to automatically log in the user upon successful creation by returning a JWT token in the response.

## Security
- **JWT Authentication:** Implemented via `JwtAuthenticationFilter`.
- **Authorization Header:** Must be `Bearer <token>`.
- **Token Expiration:** 24 hours.
- **CSRF:** Disabled.
- **CORS:** Currently not explicitly configured (Standard Spring defaults).
- **Public Endpoints:** `/api/auth/**`, `/api/users/**`, `/h2-console/**`, `/swagger-ui/**`.
- **Protected Endpoints:** `/api/tasks/**`.

## Development Data (user.sql)
- **User 1:** `user1@example.com` / `123`
- **User 2:** `user2@example.com` / `123`
