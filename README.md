# Spring Boot CRUD Application for Runner Management

## Overview
This project is a **Spring Boot** application built using the **Spring MVC Framework** and **Java SDK 17**. It connects to a **PostgreSQL** database for data persistence and supports CRUD operations for managing runner records. The application also integrates with a user service to fetch user details through REST API endpoints.

---

## Prerequisites

### Required Tools:
- **Java SDK 17**
- **SDKMAN** (to manage Java SDK versions)
- **Docker** and **Docker Compose** (to set up PostgreSQL)
- **Postman** (to test REST APIs)
- **Curl** (optional, for command-line API testing)

---

## Installation Steps

### 1. Install SDKMAN
SDKMAN helps manage multiple Java versions easily.

```bash
# Install SDKMAN
curl -s "https://get.sdkman.io" | bash

# Reload the shell
source "$HOME/.sdkman/bin/sdkman-init.sh"

# Verify installation
sdk version

# Install Java SDK 17
sdk install java 17.0.8-tem
```

### 2. Install Docker and Docker Compose
Docker is used to run a PostgreSQL database in a containerized environment.

- **For Ubuntu**:
  ```bash
  sudo apt update
  sudo apt install -y docker.io docker-compose
  sudo systemctl start docker
  sudo systemctl enable docker
  ```

- **For Mac** or **Windows**:
  Download Docker Desktop from [Docker's official site](https://www.docker.com/products/docker-desktop) and follow the installation instructions.

### 3. Install Postman
Download and install Postman from [Postman’s official site](https://www.postman.com/downloads/).

### 4. Set Up PostgreSQL Using Docker
Create a `docker-compose.yml` file in your project root with the following content:

```yaml
version: '3.8'
services:
  postgres:
    image: postgres:15
    container_name: postgres_runner
    ports:
      - "5432:5432"
    environment:
      POSTGRES_USER: runner_user
      POSTGRES_PASSWORD: runner_password
      POSTGRES_DB: runner_db
    volumes:
      - postgres_data:/var/lib/postgresql/data
volumes:
  postgres_data:
```

Start the PostgreSQL container:

```bash
docker-compose up -d
```

---

## Running the Application

### 1. Clone the Repository
```bash
git clone <repository-url>
cd <repository-folder>
```

### 2. Run the Spring Boot Application
```bash
./mvnw spring-boot:run
```

---

## API Endpoints

### Runner Management Endpoints

#### 1. Create a Run
**Endpoint:**
```
POST /api/runs/create
```
**Curl Command:**
```bash
curl --location 'http://localhost:8080/api/runs/create/' \
--header 'Content-Type: application/json' \
--data '{
    "id": 1,
    "title": "Morning Run",
    "startedOn": "2024-12-18T06:30:00",
    "completedOn": "2024-12-18T07:30:00",
    "miles": 5,
    "location": "OUTDOOR"
}'
```

#### 2. Get All Runs
**Endpoint:**
```
GET /api/runs
```
**Curl Command:**
```bash
curl --location --request GET 'http://localhost:8080/api/runs'
```

#### 3. Get Run by ID
**Endpoint:**
```
GET /api/runs/{id}
```
**Curl Command:**
```bash
curl --location --request GET 'http://localhost:8080/api/runs/1' \
--header 'Content-Type: application/json'
```

#### 4. Update a Run
**Endpoint:**
```
PUT /api/runs/update/{id}
```
**Curl Command:**
```bash
curl --location --request PUT 'http://localhost:8080/api/runs/update/1' \
--header 'Content-Type: application/json' \
--data '{
    "title": "Evening Run",
    "startedOn": "2024-12-18T18:30:00",
    "completedOn": "2024-12-18T19:30:00",
    "miles": 6,
    "location": "OUTDOOR"
}'
```

#### 5. Delete a Run
**Endpoint:**
```
DELETE /api/runs/delete/{id}
```
**Curl Command:**
```bash
curl --location --request DELETE 'http://localhost:8080/api/runs/delete/1'
```

### User Service Endpoints

#### 1. Get All Users
**Endpoint:**
```
GET /api/users
```
**Curl Command:**
```bash
curl --location --request GET 'http://localhost:8080/api/users'
```

#### 2. Get User by ID
**Endpoint:**
```
GET /api/users/{id}
```
**Curl Command:**
```bash
curl --location --request GET 'http://localhost:8080/api/users/1'
```

---

## Technologies Used
- **Spring Boot**: Backend framework for rapid development.
- **PostgreSQL**: Database for data persistence.
- **Docker**: For containerized database setup.
- **Postman**: For API testing.
- **Java SDK 17**: Latest stable version of Java.

---

## Additional Notes
1. Ensure Docker is running when starting the application.
2. Replace placeholders like `{id}` in the API URLs with actual values.
3. Use Postman or Curl for testing endpoints as shown in the examples.

Feel free to contribute and enhance the project! 😊


