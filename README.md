# WebServer (Java)

This project demonstrates the difference between a **Single-Threaded Server** and a **Multi-Threaded Server** in Java.  
It shows how concurrency affects performance when handling multiple client requests.

---

## 📂 Project Structure

```
WebServer/
├── src/main/java/
│ ├── SingleThreaded/ # Single-threaded server implementation
│ └── MultiThreaded/ # Multi-threaded server implementation
├── build/ # Build output (Gradle)
├── .gradle/ # Gradle settings
└── test/ # Unit tests
```

---

## 🚀 How to Run

### 1. Clone the repository
```bash
git clone https://github.com/nayssing/WebServer.git
cd WebServer

./gradlew build   # Linux / macOS
gradlew.bat build # Windows

javac Server.java
java Server

javac Client.java
java Client
```

Comparison

Single-Threaded Server
Processes one request at a time. If a request takes long, all others must wait.

Multi-Threaded Server
Creates a new thread per client connection, allowing multiple clients to be served concurrently.

🛠️ Tech Stack

Language: Java

Build Tool: Gradle


