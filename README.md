# 🎓 Student Grader — Backend

> A full-stack **online exam & auto-grading platform** built with Spring Boot. Students register, log in, attempt MCQ-based tests, and receive instant scores. Admins manage questions and monitor results via a secure dashboard.

🌐 **Live Demo:** [grader-frontend-final-1zmb0eznt-ajimtamboli-webs-projects.vercel.app](https://grader-frontend-final-1zmb0eznt-ajimtamboli-webs-projects.vercel.app/)
📦 **Frontend Repo:** [Grader-Frontend (React.js)](https://github.com/AjimTamboli-Web/Grader-Frontend)

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3.5.4 |
| Security | Spring Security + BCrypt |
| Database | MySQL |
| ORM | Spring Data JPA / Hibernate |
| Caching | Redis (Spring Cache) |
| Email | SendGrid |
| Build Tool | Maven |
| Utilities | Lombok |
| Deployment | Railway / Render |

---

## 🗂️ Project Structure

```
src/main/java/com/StudentGrader/
├── Entity/
│   ├── Admin.java           → Admin authentication entity
│   ├── Student.java         → Student profile + final score
│   ├── Question.java        → MCQ question bank
│   └── StudentAnswer.java   → Student's answers (junction entity)
├── Controller/
│   ├── AdminController.java
│   ├── StudentController.java
│   └── QuestionController.java
├── Service/
│   ├── AdminService.java
│   ├── StudentService.java
│   └── QuestionService.java
├── Repository/
│   ├── AdminRepository.java
│   ├── StudentRepository.java
│   ├── QuestionRepository.java
│   └── StudentAnswerRepository.java
├── Security/
│   ├── SecurityConfig.java
│   └── JwtFilter.java
└── StudentGraderApplication.java
```

---

## 🗄️ Database Schema

### `admin`
| Column | Type | Constraint |
|---|---|---|
| `id` | INT | PK, Auto Increment |
| `email` | VARCHAR | NOT NULL, UNIQUE |
| `password` | VARCHAR | NOT NULL (BCrypt hashed) |
| `role` | VARCHAR | Default = `"ADMIN"` |

### `student`
| Column | Type | Constraint |
|---|---|---|
| `id` | INT | PK, Auto Increment |
| `name` | VARCHAR | — |
| `email` | VARCHAR | — |
| `mobile` | VARCHAR | — |
| `batch` | VARCHAR | — |
| `password` | VARCHAR | BCrypt hashed |
| `final_score` | INT | Nullable; set after quiz submission |

### `question`
| Column | Type | Constraint |
|---|---|---|
| `id` | INT | PK, **manually assigned** |
| `question_text` | VARCHAR | — |
| `option_a` | VARCHAR | — |
| `option_b` | VARCHAR | — |
| `option_c` | VARCHAR | — |
| `option_d` | VARCHAR | — |
| `correct_answer` | VARCHAR | `"A"`, `"B"`, `"C"`, or `"D"` |

### `student_answer`
| Column | Type | Constraint |
|---|---|---|
| `id` | BIGINT | PK, Auto Increment |
| `student_id` | INT | FK → `student.id` |
| `question_id` | INT | FK → `question.id` |
| `selected_answer` | VARCHAR | Student's chosen option |

**Entity Relationships:**
```
student  ──(1:N)──  student_answer  ──(N:1)──  question
admin  (standalone — auth only)
```

---

## 🔐 Security

- **BCrypt** password hashing for both Admin and Student passwords
- **Spring Security** for endpoint protection
- Public endpoints: `/admin/login`, `/student/register`, `/student/login`
- All other endpoints require authentication
- **CORS** configured to allow requests from the Vercel frontend domain

---

## 📡 API Endpoints

### Admin
| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/admin/login` | Admin login |
| `GET` | `/admin/students` | Get all students |
| `GET` | `/admin/results` | View all student scores |

### Student
| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/student/register` | Register new student |
| `POST` | `/student/login` | Student login |
| `GET` | `/student/{id}` | Get student profile |
| `POST` | `/student/submit` | Submit quiz answers |
| `GET` | `/student/{id}/score` | Get final score |

### Questions
| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/questions` | Fetch all questions for exam |
| `POST` | `/questions/add` | Admin: add a question |
| `DELETE` | `/questions/{id}` | Admin: delete a question |

---

## ⚙️ Getting Started

### Prerequisites
- Java 17+
- Maven 3.8+
- MySQL 8+
- Redis (for caching)

### 1. Clone the repository
```bash
git clone https://github.com/AjimTamboli-Web/Student-Grader-BackEnd.git
cd Student-Grader-BackEnd
```

### 2. Configure `application.properties`
```properties
# Database
spring.datasource.url=jdbc:mysql://localhost:3306/student_grader
spring.datasource.username=ajim
spring.datasource.password=mysql_password
spring.jpa.hibernate.ddl-auto=update

# Redis
spring.data.redis.host=localhost
spring.data.redis.port=6379

# SendGrid (Email)
sendgrid.api.key=sendgrid_api_key

# CORS
frontend.url=https://grader-frontend-final-git-main-ajimtamboli-webs-projects.vercel.app/
```

### 3. Build and Run
```bash
./mvnw clean package
java -jar target/StudentGrader-0.0.1-SNAPSHOT.jar
```

The server starts at: `http://localhost:8080`

---

## 🚀 Deployment

| Component | Platform |
|---|---|
| Frontend | Vercel (auto-deploy on push to `main`) |
| Backend | Railway |
| Database | Railway MySQL |
| Cache | Redis Cloud |

**Build command:**
```bash
mvn clean package -DskipTests
```
**Run command:**
```bash
java -jar target/StudentGrader-0.0.1-SNAPSHOT.jar
```

---

## 📦 Key Dependencies (`pom.xml`)

```xml
spring-boot-starter-web          → REST API
spring-boot-starter-data-jpa     → ORM / Hibernate
spring-boot-starter-security     → Auth & endpoint protection
spring-security-crypto           → BCrypt password encoding
spring-boot-starter-cache        → Caching abstraction
spring-boot-starter-data-redis   → Redis integration
mysql-connector-j                → MySQL JDBC driver
sendgrid-java (4.10.3)           → Email notifications
lombok                           → Boilerplate reduction
spring-boot-devtools             → Hot reload (dev only)
```

---

## 👨‍💻 Author

**Ajim Tamboli**
- GitHub: [@AjimTamboli-Web](https://github.com/AjimTamboli-Web)

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
