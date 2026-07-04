# IoT Telemetry API

A Spring Boot REST service for registering IoT devices and (in progress) ingesting/querying their telemetry data. Built as a backend layer on top of my serverless IoT data ingestion pipeline.

## Tech Stack
- Java 17, Spring Boot 4.1
- Spring Web, Spring Data JPA
- H2 (in-memory database)

## Endpoints
- `POST /devices` — register a new device
- `GET /devices` — list all devices
- `GET /devices/{id}` — get a single device by id

## Run locally
Then the API is available at `http://localhost:8080`.

## Example
curl -X POST http://localhost:8080/devices 
-H "Content-Type: application/json" 
-d '{"name":"sensor-1","type":"temperature","status":"online"}'

## Roadmap
- [ ] TelemetryReading entity + time-range queries
- [ ] Input validation and error handling
- [ ] Unit tests
