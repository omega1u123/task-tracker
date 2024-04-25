# TaskTracker

Multi-user task scheduler. Users can utilize it as a TODO list. The project is inspired by Trello.

## Technologies

- Java
- Maven
- Spring Boot, Spring Security, Spring AMQP, Spring Scheduler, Spring Mail
- PostgreSQL, Spring Data JPA
- Docker 
- RabbitMQ

### Backend

Spring Boot (Java) application implementing a REST API for user management, sessions created upon authorization, and tasks.

REST-SERVICE:

User:
- Registration
- Authorization
- Logout

Task:
- Create
- Edit
- Delete

Upon user registration, the service sends a message to RabbitMQ with the user's email address.

EMAIL-SENDER:

Upon receiving messages from RabbitMQ, it sends an email to the users' email addresses.

SCHEDULER: 

Every day at 00:00, it sends messages to RabbitMQ with a report on users' completed tasks.