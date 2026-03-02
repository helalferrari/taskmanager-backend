# TaskManager Backend - Project Documentation

This document serves as a persistent knowledge base for the TaskManager Backend project, documenting the architectural decisions, entity structures, and API configurations.

## Architecture
The project follows the **MVC (Model-View-Controller)** pattern using **Spring Boot 3.4.x** (configured for Java 25).

### Tech Stack
- **Framework:** Spring Boot (Web, Data JPA, Security)
- **Database:** H2 (In-memory for development)
- **Utilities:** Lombok, Jakarta Persistence (JPA)

## Database Configuration (`application.properties`)
- **URL:** `jdbc:h2:mem:taskdb`
- **User/Password:** `sa` / (empty)
- **H2 Console:** Enabled at `/h2-console`
- **Hibernate DDL:** `update` (automatic table creation)

## Entities

### User (`com.helalferrari.taskmanager.model.User`)
| Attribute | Type | Constraints |
| :--- | :--- | :--- |
| `id` | UUID | Primary Key (Auto-generated) |
| `email` | String | Unique, Not Null |
| `password` | String | Not Null |

### Task (`com.helalferrari.taskmanager.model.Task`)
| Attribute | Type | Constraints |
| :--- | :--- | :--- |
| `id` | UUID | Primary Key (Auto-generated) |
| `user` | User | Foreign Key (`user_id`), Not Null |
| `title` | String | Not Null |
| `completed` | boolean | Not Null |
| `deleted` | boolean | Not Null |
| `createDate`| Date | Auto-generated on persist |
| `modifyDate`| Date | Auto-updated on update |

## API Endpoints

### Tasks
- `GET /api/tasks?userId={UUID}`: Lists all tasks for a specific user.

### Users
- `POST /api/users`: Creates a new user.
- `GET /api/users/{id}`: Retrieves user details by ID.

## Security
- **CSRF:** Disabled for development.
- **Access Control:** All `/api/**` and `/h2-console/**` endpoints are currently permitted for easier testing.
- **X-Frame-Options:** Set to `SAMEORIGIN` to allow H2 Console usage.

## Repository Custom Methods
- `TaskRepository.findByUserId(UUID userId)`: Custom query to filter tasks by the associated user's ID.
