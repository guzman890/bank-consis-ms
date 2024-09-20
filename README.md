# bank-consis-ms

## How to Run

### Prerequisites
- Java 11 or higher
- Gradle

### Build the Project
To build the project, run the following command:
```sh
./gradlew build
```

### Run the Project
To run the project, run the following command:
```sh
./gradlew bootRun
```
### Access Swagger UI
Once the application is running, you can access the Swagger UI at:
```
http://localhost:8080/api/swagger-ui.html
```
### Database Initialization
With h2 all initialization is done in the schema.sql and data.sql file.

### Request Examples

To get reports for a specific customer within a date range, use the following curl command: It work now because we have test data.
```sh
curl --location 'http://localhost:8080/api/reportes?customerId=12&startDate=2023-01-01&endDate=2023-01-31'
```

To create a new client, use the following curl command example:
```sh
curl --location 'http://localhost:8080/api/clientes' \
--header 'Content-Type: application/json' \
--data '{
  "name": "John Doe",
  "gender": "MALE",
  "age": 30,
  "identification": "1234567890",
  "address": "123 Main St",
  "phone": "555-1234",
  "password": "password123",
  "status": "ACTIVE"
}'
```

other examples are in the postman file: (Bank API.postman_collection) or swagger documentation.