# 🚀 Career Center Portal Backend (Java Edition)

A high-performance, enterprise-grade backend for the **New Uzbekistan University Career Center**.

This project is a complete migration from a legacy **Node.js prototype** to a robust **Spring Boot 3 + PostgreSQL** architecture.  
It maintains **full API compatibility** with the existing React frontend while introducing:

- Strong type safety
- ACID-compliant transactions
- Advanced relational + JSON data modeling

---

## 🏗️ System Architecture

The application follows a strict **Layered Architecture** (Separation of Concerns) to ensure scalability, maintainability, and testability.

### 📦 Package Structure

```text
com.careercenter
├── config          # Security, CORS, application configuration
├── controller      # REST controllers (API layer)
├── dto             # Data Transfer Objects & validation
├── entity          # JPA entities
├── repository      # Spring Data JPA repositories
├── security        # JWT, filters, authentication logic
├── service         # Business logic
├── seeder          # Data migration & initialization
└── CareerCenterApplication.java
```

---

## ⚡ Key Technical Features

### 🗄️ Hybrid Database Model (PostgreSQL)

To handle complex academic data (nested semesters, grades, research papers) without creating hundreds of tables, the system uses **PostgreSQL JSONB**.

- **Searchable Fields**  
  Core data such as GPA, Major, Email, and Status are stored as standard SQL columns for fast filtering and analytics.

- **Complex Data**  
  Deeply nested and flexible structures are stored in optimized `jsonb` columns, preserving legacy data without loss.

---

### ✅ Robust Input Validation

Strict validation is enforced at the **DTO level**:

- **ID Format Validation**  
  Ensures Student and Staff IDs match university patterns (e.g., `PU...`, `NU...`) before database interaction.

- **Data Integrity Rules**  
  Prevents invalid emails, negative values, and malformed academic records.

---

### 🔄 Smart Data Migration (Seeder)

An automated **DataSeeder** runs on application startup:

- Reads legacy `.json` data files
- Transforms them into Java entities
- Hashes passwords securely
- Populates PostgreSQL automatically

No manual SQL or imports required.

---

## 🐳 How to Run (Docker) — Recommended

The entire system (Backend API + PostgreSQL) is fully containerized.

### Prerequisites
- Docker Desktop installed

### Steps

1. **Environment Configuration**
   - Ensure a `.env` file exists in the project root (contains database credentials).

2. **Run the System**
   ```bash
   docker-compose up --build
   ```

3. **Access**
   - API: `http://localhost:4000/api`
   - Database: Accessible internally via Docker network

---

## 🛠️ How to Run (Manual / Local)

If you prefer running without Docker:

### Requirements
- PostgreSQL running on port `5432`
- Java 17+
- Maven

### Steps

1. Update `.env` or `application.properties` with local DB credentials.
2. Run:
   ```bash
   mvn spring-boot:run
   ```

---

## 🔐 Authentication & Roles

The system implements a **unified login mechanism**.  
Authentication is routed automatically to the correct user table based on email existence.

### Demo Accounts

| Role        | Email                         | Password        |
|------------|-------------------------------|-----------------|
| Student    | f.hayitov@newuu.uz            | student123     |
| Staff      | s.tuychiyev@newuu.uz          | staff@123      |
| Professor  | elon.mask@newuu.uz            | professor123   |
| Industry   | hr@artel.uz                   | industry123    |

---
# API Endpoints

## Authentication

- **POST** `/api/auth/login`  
  Unified login endpoint for **Students**, **Staff**, **Professors**, and **Industry** partners

## Student Module

- **GET** `/api/students`  
  List students with support for filtering  
  Query parameters:
    - `?search=`
    - `?major=`
    - `?minGpa=`
    - (and other filters)

- **GET** `/api/students/stats`  
  Real-time analytics and statistics (used for Dashboard)

- **GET** `/api/students/{id}`  
  Get single student by ID

- **GET** `/api/students/by-email?email=...`  
  Get student by email

## Job & Industry Module

- **GET** `/api/jobs`  
  List all job postings

- **POST** `/api/jobs`  
  Create and persist a new job posting  (Industry only)

- **GET** `/api/industry`  
  List industry partners/companies

- **GET** `/api/industry/by-email?email=...`  
  Get industry partner by email

## Staff & Faculty Module

- **GET** `/api/staff`  
  List staff members

- **GET** `/api/professors`  
  List professors

- **GET** `/api/professors/{id}`  
  Get single professor by ID

---

## 👨‍💻 Lead Developer

**Otabek Abdumalikov**
