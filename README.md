# 🌐 Real-Time Global Chat Server

A full-duplex, real-time messaging architecture built with **Spring Boot** and **WebSockets**. This project demonstrates persistent, low-latency communication between multiple clients using the STOMP protocol, backed by a relational database for message persistence.

## 🚀 Architecture Overview

Unlike traditional RESTful APIs that rely on stateless HTTP polling, this application establishes a persistent TCP connection between the client and the server. 

1. **The Handshake:** Clients initiate an HTTP `Upgrade` request to the `/ws` endpoint.
2. **The Protocol:** Once established, communication switches to WebSockets, utilizing **STOMP** (Simple Text Oriented Messaging Protocol) as the sub-protocol for message routing.
3. **The Broker:** An in-memory Spring message broker routes payloads from publishers (`/app/chat.sendMessage`) to active subscribers (`/topic/public`).
4. **The Persistence:** Incoming payloads are deserialized via Jackson, persisted to a **MySQL** database using Spring Data JPA, and immediately broadcasted to the network.

## 🛠️ Tech Stack

**Backend System:**
* **Java 17+**
* **Spring Boot 3.x** (Web, WebSockets, Data JPA)
* **MySQL** (Relational Database)
* **Hibernate** (ORM)

**Frontend Client:**
* **Vanilla JavaScript & HTML5** (Lightweight, zero-dependency testing client)
* **SockJS** (WebSocket fallback protocol)
* **Stomp.js** (Messaging protocol client)

## ✨ Key Features
* **Zero-Latency Broadcasting:** Instantaneous message delivery across all connected nodes without page refreshes.
* **Persistent Connection:** Avoids the overhead of continuous HTTP request/response headers.
* **Fault Tolerance:** Implements SockJS to automatically downgrade to HTTP long-polling if corporate firewalls block native WebSockets.
* **Database Integration:** Full chat history is automatically persisted to MySQL with auto-generated timestamps.

---

## 💻 Local Setup & Installation

### Prerequisites
* Java Development Kit (JDK) 17 or higher
* Maven
* MySQL Server running locally on port `3306`

### 1. Database Configuration
Create a new MySQL database named `chat_app_db`. Update the `src/main/resources/application.properties` file with your local database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/chat_app_db
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
