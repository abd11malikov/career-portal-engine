# 🚀 Career Center Portal Backend

A production-grade backend system designed to connect 1,200+ university students with global industry partners.

This project is a high-performance migration from an Express.js prototype to a **Spring Boot 3** and **PostgreSQL** architecture, ensuring data integrity, security, and scalability for New Uzbekistan University.

---

## 🏛️ Architecture & Database Strategy
The system uses a **Hybrid Relational-Document Model** to handle the high complexity of academic data:
- **Relational SQL:** Core fields (GPA, Major, Email, Status) are extracted into standard PostgreSQL columns for lightning-fast filtering and analytics.
- **PostgreSQL JSONB:** Complex nested data structures (Research papers, multi-year course history, work experience) are stored in optimized `jsonb` columns, providing NoSQL-like flexibility within a SQL environment.

## 🛠️ Technical Stack
- **Language:** Java 21
- **Framework:** Spring Boot 3.x (Web, Data JPA, Security)
- **Database:** PostgreSQL (Cloud-hosted on Neon.tech)
- **Data Handling:** Jackson & Hypersistence-Utils (for JSONB mapping)
- **Deployment:** Docker (Multi-stage build)

## 🔌 API Features
### 👨‍🎓 Student Management
- **Dynamic Filtering:** Search by GPA range, Major, or Employment Status using `JpaSpecification`.
- **Analytics:** `/api/students/stats` provides real-time distribution charts of student success metrics.

### 💼 Job & Industry Portal
- **Role-Based Access:** Industry partners can post opportunities; students can browse and apply.
- **Deep Nesting:** Support for complex nested professor profiles and research data.

## 🚀 Getting Started
### Local Development
1. Configure your `.env` or Environment Variables:
    - `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`
2. Run the application:
   ```bash
   mvn spring-boot:run