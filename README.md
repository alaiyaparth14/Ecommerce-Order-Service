# Ecommerce Order Service

A Spring Boot application that implements **Order Placement** functionality for an e-commerce system. It handles **product management**, **stock validation**, **order creation**, and **payment simulation** using layered architecture and testable components.

---

## Technologies & Tools

| Technology         | Version       |
|--------------------|---------------|
| Java               | 17            |
| Spring Boot        | 3.2.x         |
| Maven              | 4.x           |
| MySQL              | 8.x           |
| JUnit              | 5.10.0        |
| Mockito            | 5.10.0        |
| Swagger/OpenAPI    | 2.5.0         |

---

##  Project Structure

```plaintext
src/
 └── main/
     ├── java/
     │   └── com.obvioustechnology.ecommerce
     │       ├── controller/       // REST controllers (Product & Order)
     │       ├── dto/              // DTOs (ProductRequest, OrderRequest, etc.)
     │       ├── model/            // Entities (Product, Order)
     │       ├── repository/       // Spring Data JPA Repositories
     │       ├── service/          // Business logic & service layer
     │       └── exception/        // Custom exceptions
     └── resources/
         └── application.properties
 └── test/
     └── java/
         └── com.obvioustechnology.ecommerce.service/
             └── OrderServiceTest.java

Key Features

Create and retrieve products

Place orders with stock lock and deduction

Simulated payment process

Layered architecture: Controller → Service → Repository

Exception handling for edge cases

Unit tests using JUnit and Mockito

Swagger UI for API testing

API Endpoints
Add Product
POST /api/products

Request :

Json
{
  "name": "Laptop",
  "price": 50000,
  "stock": 10
}

Response : 
{
  "id": 1,
  "name": "Laptop",
  "price": 50000.0,
  "stock": 10,
}

Place Order
POST /api/orders

Request : 

Json
{
  "productId": 1,
  "quantity": 5
}

Response :
{
  "id": 1,
  "product": {
    "id": 1,
    "name": "Laptop",
    "price": 50000.0,
    "stock": 5
  },
  "quantity": 5,
  "status": "CONFIRMED",
  "totalPrice": 250000.0,
  "orderTime": "2025-08-03T02:35:09.3570468"
}

check Swagger (`localhost:8080/swagger-ui/index.html`) is enabled.




