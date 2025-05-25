# PriceComparator – Spring Boot Project

## Description
PriceComparator is a Java Spring Boot application for comparing product prices across multiple stores. It allows users to:
- View daily and top discounts
- Simulate an optimized shopping basket based on best available prices
- Set and manage price alerts
- Filter historical price data by store, brand, and category
- Receive product recommendations by name or category

The project demonstrates a well-structured REST API architecture, efficient use of DTOs and JPA repositories, SQL query optimization, and application of the Template Method design pattern.

---

## Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- H2 (in-memory database)
- Maven
- RESTful APIs
- Postman (for testing)

---

## Project Structure
```text
src/
├── controller/           → REST API endpoints
├── service/              → Business logic layer
├── repository/           → Data access layer (JPA)
├── dto/                  → Data Transfer Objects
├── model/                → JPA entities (Product, Store, Discount, PriceEntry, etc.)
├── data/                 → CSV reading logic (Template Method pattern)
├── resources/csv/        → Product and discount data files
└── PricecomparatorApplication.java
```

---

## How to Run
```bash
# Clone the repository
https://github.com/visanageorge/price-comparator.git
cd price-comparator

# Build and run
mvn spring-boot:run
```
App will be available at:
```
http://localhost:8080
```

---

## 🔗 API Endpoints (Postman-ready)

### Discounts
- `GET /api/discounts/last-24-hours`
- `GET /api/discounts/best-discounts`

### Alerts
- `POST /api/alert/set-price-alert`
```json
{
  "productId": "P001",
  "targetPrice": 10
}
```
- `GET /api/alert/get-alerts`
- `DELETE /api/alert/delete-price-alert?productId=P001`

### Basket Simulation
- `POST /api/shopping/basket`
```json
[
  { "productName": "lapte zuzu", "quantity": 2 },
  { "productName": "banane", "quantity": 2 },
  { "productName": "detergent lichid", "quantity": 2 },
  { "productName": "cafea măcinată", "quantity": 2 },
  { "productName": "vin alb demisec", "quantity": 2 },
  { "productName": "cartofi albi", "quantity": 2 }
]
```

### Recommendations
- `GET /api/products/product-recommendations?productName=șampon`
- `GET /api/products/category-recommendations?productCategory=lactate`

### Price History
- `GET /api/price-entry/price-history?store=lidl&brand=Lidl&category=legume%20și%20fructe`

---

## Notes
- CSV file import uses Template Method pattern (`CsvImporter` as abstract base class)
- SQL logic is optimized using `JOIN`, `ROW_NUMBER`, `CASE`, and `CURRENT_DATE`
- DTOs ensure clean separation between API and persistence layers

---

## Author
Developed by Visana George-Vladut as part of a hands-on internship-level academic project.

---

