:# User Management Service - Getting Started

### JVM Version: 11

### Build and Run
1. ``gradle build``
2. ``gradle run``

### Useful links:
- http://localhost:8080/swagger - Swagger UI
- http://localhost:8081/admin/ - Admin panel

# Task

We want you to build a new user management service. The service must offer CRUD operations
for the users. The users must be unique, they should have basic information and their first, last
name must not exceed 100 characters.

In order to achieve this, we want you to build a new service that exposes an API with the
following specifications:

1) Creates new user    
   a. The API should accept only valid data
2) Returns all the users
3) Returns a user by ID
4) Deletes a user
5) Updates a users data
