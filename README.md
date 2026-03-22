# Twitchy.com
A scalable, distributed URL shortening service built with a focus on low-latency redirection and asynchronous analytics. This project demonstrates a production-ready approach to handling millions of short-link mappings using a message-driven architecture.

![Java](https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2-brightgreen?style=for-the-badge&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?style=for-the-badge&logo=postgresql)
![Redis](https://img.shields.io/badge/Redis-Cache-red?style=for-the-badge&logo=redis)

---

## 🚀 Key Technical Features

* **Deterministic Encoding:** Implements a custom **Base62 conversion** algorithm mapped to database sequences for collision-free short codes.
* **High-Speed Redirection:** Leverages **Redis caching** to achieve sub-10ms redirection, bypassing the primary database for frequent lookups.
* **Asynchronous Analytics:** Offloads click analytics and metadata collection to **AWS SQS**. This ensures the core redirect logic remains non-blocking.
* **URL Deduplication:** Utilizes an **MD5 Hashing layer** and PostgreSQL **Hash Indexing** to prevent redundant storage while maintaining $O(1)$ performance.

---

## 🛠️ Tech Stack

| Layer | Technology |
| :--- | :--- |
| **Language** | Java 25 (OpenJDK) |
| **Framework** | Spring Boot 3.x |
| **Database** | PostgreSQL (Persistence) |
| **Caching** | Redis (Read-Optimization) |
| **Build Tool** | Gradle |

---

## 📐 System Design

The architecture follows a "Write-Through" cache pattern combined with an event-driven analytics pipeline:

1.  **Request:** User submits a long URL.
2.  **Deduplication:** System generates an **MD5 hash** to check for existing entries.
3.  **Persistence:** URL is saved; the **ID** is converted to a **Base62 string** (e.g., `1024 -> "gw"`).
4.  **Caching:** The mapping is mirrored in **Redis**.


---

## 🚦 Getting Started

### Prerequisites
* JDK 25
* Docker Desktop
* AWS CLI (or LocalStack)

### Installation & Run
```bash
# 1. Clone the repository
git clone [https://github.com/barsh-dev/url-shortener.git](https://github.com/barsh-dev/url-shortener.git)

# 2. Spin up PostgreSQL & Redis
docker-compose up -d

# 3. Build and Run the Spring Boot App
./gradlew bootRun