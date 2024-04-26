# Task tracker

Multi-user task scheduler. Users can utilize it as a TODO list. The project is inspired by Trello.

## Technologies

- Java
- Maven
- Spring Boot, Spring Security, Spring AMQP, Spring Scheduler, Spring Mail
- PostgreSQL, Spring Data JPA, Hibernate
- Docker 
- RabbitMQ

### Backend
![Снимок экрана от 2024-04-25 18-21-40.png](%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%20%D0%BE%D1%82%202024-04-25%2018-21-40.png)
Spring Boot (Java) application implementing a REST API for user management, sessions created upon authorization, and tasks.

#### REST-SERVICE:

User:
- Registration
- Authorization
- Logout

Task:
- Create
- Edit
- Delete

Upon user registration, the service sends a message to RabbitMQ with the user's email address.

#### EMAIL-SENDER:

Upon receiving messages from RabbitMQ, it sends an email to the users' email addresses.

#### SCHEDULER: 

Every day at 00:00, it sends messages to RabbitMQ with a report on users' completed tasks.