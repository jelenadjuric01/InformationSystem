# Video Store — Distributed Information System

A Java EE information system for a video streaming platform, built as a distributed application around a REST front-end and JMS-based back-end subsystems.

The console client talks to the server over HTTP/REST. The server forwards each request to one of three back-end subsystems over JMS using the Command pattern, and the addressed subsystem persists the result to MySQL via JPA.

```
+---------+   HTTP/REST    +----------+    JMS (Command)    +----------------+    JPA    +-------+
|  Client | <------------> |  Server  | <-----------------> |  Subsystem 1   | <-------> | MySQL |
| (CLI)   |                | (JAX-RS) |                     |  Subsystem 2   |           |       |
|         |                |          |                     |  Subsystem 3   |           |       |
+---------+                +----------+                     +----------------+           +-------+
```

## Domain

A video viewing service that stores:

- **Users** — name, email, age, gender, and town of residence
- **Towns** — locations users belong to
- **Videos** — title, duration, owner (a user), and upload timestamp
- **Categories** — many-to-many with videos (`belongs`)
- **Packages** — current monthly price
- **Subscriptions** — one-month subscription of a user to a package, priced at the package price at subscription time; a user cannot have more than one active subscription
- **Watched** — when a user started watching a video, from which second, and how many seconds were played
- **Ratings** — a user's 1–5 score for a video, with a timestamp

## Architecture

### Server (`java/Server`)

- Java EE 8 (Jakarta EE 8) WAR deployed on GlassFish/Payara
- Exposes JAX-RS REST endpoints under `/Server/` (one resource per aggregate: `user`, `town`, `video`, `category`, `package`, `subscription`, `watched`, `rating`)
- Each REST endpoint builds a `Command` and dispatches it to the appropriate subsystem via `JMSCommunication`, which sends an `ObjectMessage` to that subsystem's queue and waits for a correlated reply on `queueServer` (5s timeout).
- Routing is encoded on the command (`Command.Endpoint` = `S1` | `S2` | `S3`).

### Subsystems

Three NetBeans/Ant Java EE application clients, each owning a slice of the domain and its own JPA persistence unit:

| Subsystem | Project dir       | Responsibilities                                        | JMS queue   |
|-----------|-------------------|---------------------------------------------------------|-------------|
| 1         | `Subsystem11`     | Users, towns                                            | `queue1`    |
| 2         | `Subsystem2`      | Videos, categories, video–category links                | `queue2`    |
| 3         | `Subsystem3`      | Packages, subscriptions, ratings, watched events        | `queue3`    |

Each subsystem runs a message loop that pulls commands off its queue, dispatches them to a per-`Command.Type` `CommandHandler`, and replies to `queueServer` correlated by `JMSCorrelationID`.

### Client (`java/Client`)

- Console application built with Maven
- Talks to the server using Retrofit + Gson against `http://localhost:8080/Server/`
- Drives a numbered text menu where each entry maps to one REST call

### Database (`bases/videostore.sql`)

MySQL dump of the `videostore` schema with seed data. Tables:
`town`, `user`, `category`, `video`, `belongs`, `package`, `subscription`, `watched`, `rating`.

### Diagrams (`uml/`)

- `uml/klase/` — class diagrams for the server and each subsystem
- `uml/sekvence/` — sequence diagram for the subscription flow

## Project layout

```
.
├── bases/
│   └── videostore.sql            # MySQL schema + seed data
├── java/
│   ├── Server/                   # Maven WAR — JAX-RS + JMS dispatcher
│   ├── Client/                   # Maven CLI — Retrofit-based menu app
│   ├── Subsystem11/              # Ant project — Users / Towns
│   ├── Subsystem2/               # Ant project — Videos / Categories
│   └── Subsystem3/               # Ant project — Packages / Subs / Ratings / Watched
└── uml/
    ├── klase/                    # Class diagrams
    └── sekvence/                 # Sequence diagrams
```

## Prerequisites

- JDK 8
- Maven 3.x (Server, Client)
- Ant + NetBeans (Subsystems — they are NetBeans Java EE application clients)
- GlassFish 5 / Payara 5 (Jakarta EE 8 server with built-in JMS broker)
- MySQL 8.x

## Setup

### 1. Database

```bash
mysql -u root -p -e "CREATE DATABASE videostore;"
mysql -u root -p videostore < bases/videostore.sql
```

### 2. Configure database credentials

Database credentials are **placeholders** in the committed config files — you must replace `YOUR_DB_USER` and `YOUR_DB_PASSWORD` with your local MySQL credentials before the server or any subsystem can connect. The four files to edit:

| File                                                              | Properties                                            |
|-------------------------------------------------------------------|-------------------------------------------------------|
| `java/Server/src/main/webapp/WEB-INF/glassfish-resources.xml`     | `User`, `Password`                                    |
| `java/Subsystem11/src/conf/persistence.xml`                       | `javax.persistence.jdbc.user`, `...jdbc.password`     |
| `java/Subsystem2/src/conf/persistence.xml`                        | same                                                  |
| `java/Subsystem3/src/conf/persistence.xml`                        | same                                                  |

Do not commit your real credentials — keep these placeholders in source control and apply the real values only to your local working copy (or override per-environment via your app server's admin console).

### 3. GlassFish / Payara — JDBC and JMS resources

The server expects:

- **JDBC**: a JDBC resource bound to JNDI name `java:app/project` pointing at the `videostore` MySQL database. The reference pool definition is in `java/Server/src/main/webapp/WEB-INF/glassfish-resources.xml` (with placeholders, see step 2).
- **JMS queues**: `queue1`, `queue2`, `queue3`, `queueServer`, all backed by the default connection factory `jms/__defaultConnectionFactory`.

Create the JMS queues, e.g.:

```bash
asadmin create-jms-resource --restype javax.jms.Queue --property Name=queue1 queue1
asadmin create-jms-resource --restype javax.jms.Queue --property Name=queue2 queue2
asadmin create-jms-resource --restype javax.jms.Queue --property Name=queue3 queue3
asadmin create-jms-resource --restype javax.jms.Queue --property Name=queueServer queueServer
```

### 4. Build and deploy the server

```bash
cd java/Server
mvn clean package
asadmin deploy target/Server-1.0-SNAPSHOT.war
```

The REST API is then available at `http://localhost:8080/Server/`.

### 5. Run the subsystems

Each subsystem is a NetBeans Java EE application client. Open `java/Subsystem11`, `java/Subsystem2`, `java/Subsystem3` in NetBeans and run the main class (`subsystem11.Subsystem1`, `subsystem2.Subsystem2`, `subsystem3.Subsystem3`). Each subsystem connects to the GlassFish JMS broker, drains its inbound queue on startup, then waits for new commands.

All three subsystems must be running for the server to handle requests end-to-end.

### 6. Run the client

```bash
cd java/Client
mvn clean package
mvn exec:java -Dexec.mainClass=com.mycompany.client.Client
```

A numbered menu appears in the terminal — pick an item to invoke the matching REST endpoint on the server.

## Tech stack

- Java 8, Jakarta EE 8 (CDI, JAX-RS, JMS, JPA)
- GlassFish/Payara 5 + built-in OpenMQ broker
- MySQL 8 + JDBC connector
- Maven (Server, Client) and Ant (Subsystems)
- Retrofit 2 + Gson (Client HTTP)

## Notes

This project was originally generated against NetBeans templates; some files (and the Subsystem build tooling) still reflect that. The Server and Client modules are imported into IntelliJ via their `pom.xml`.
