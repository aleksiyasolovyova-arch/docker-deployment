# Infrastructure 3 - Tarot Reading
**Student:** Aleksiya Solovyova

**Class:** 201

**Email:** aleksiya.solovyova@student.kdg.be

## Project Overview
The goal of this project is to set up a containerized environment through Docker Compose, ultimately deploying two copies of the same application(**Tarot-Reading**) on different paths. The whole system includes multiple service, amongst which the application backend, a database, a reverse proxy and automated SSL certificate generation through Certbot with DuckDNS.

---

## Docker Compose Setup

The `compose.yaml` file defines *6 containers*:

- **`as_duckdns`**: \
    Preforms an automatic update of the current public address to DuckDNS, extracting credentials from the `.env` file.

- **`as_certduck`**: \
    Building from the `as_duckcert` directory, it runs a Certbot container to obtain SSL certificates. It uses the DNS challenge method with DuckDNS.

- **`as_tarot` and `as_tarot1`** \
    The Spring Boot applications, built from the source through a multistage Dockerfile. Each of the applications has its own container, while connecting to the same PostgreSQL database.

- **`as_db`**: \
    The PostgreSQL database instance, used for storing data for both applications.

- **`as_nginx`**: \
    Reverse Proxy, routing client requests to the appropriate application based on the path. Uses the SSL certificates provided by *`as_certduck`*. The configuration is templated under `./nginx/conf/`.

---

## Dockerfiles
The project contains **two** custom Dockerfiles, each located in the root of their respective folders:

- **`tarot-read`** \
    Multistage Docker build, using the Gradle wrapper (`./gradlew`) to build and package the application as an executable JAR.

- **`as_duckcert`** \
    Extends the Certbot image and installs `certbot-dns-duckdns` for DNS-based SSL certificate granting.

---

## Environment Config
The `.env` file is configured for local use and includes environmental variables for the database, as well as for DuckDNS webip, token and domain name. This ensures clarity and efficiency of code, as well as centralization.


## Main network and volume

- An isolated network(`as_network`) ensures connectivity of the application and database.
- A volume(`postgres_data`)-persistent storage for the database.

# Application

### Tarot-Read
A public platform where users can check their daily tarot card, providing a glimpse into their possible future.

### Run Instructions

1. To start up:
```
docker compose up
```
2. After a bit of wait, go to your browser and access https://as-tarot-prod.duckdns.org to see the first copy of the application running. If you input in the url, instead of **`/tarot1/`**, **`/tarot2/`**, you will see the second one.
3. To stop it and remove everything:
```
docker compose down -v --remove-orphans --rmi all
```