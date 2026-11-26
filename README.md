# BILLING SYSTEM USING SPRING BOOT

A Billing System developed using Spring Boot to manage billing operations, generate invoices, and maintain customer and transaction records efficiently.

## Features
- Login / Logout using Spring Security
- Home Dashboard
- Add Items
- Update Items
- Delete Items with confirmation
- Product Report with total count
- Input validation for product details
- MySQL database integration

## Technologies Used
- Java (JDK 17 or newer)
- Spring Boot
- Spring Security
- Thymeleaf
- MySQL
- Maven
- HTML, CSS

## Requirements
- JDK 17 or newer
- Maven 3.8+
- MySQL 8 (XAMPP MySQL also works)

## Default Login
- Username: `admin`
- Password: `admin123`

## How to Run

1. Start MySQL using XAMPP.
2. Open the project in your IDE.
3. Configure your MySQL credentials in `src/main/resources/application.properties`.
4. Run the application:

```bash
mvn spring-boot:run
```

5. Open your browser and visit:

`http://localhost:8080`

## Project Pages

| URL | Purpose |
|---|---|
| `/login` | Login |
| `/home` | Dashboard |
| `/items/add` | Add Product |
| `/items/update` | Update Product |
| `/items/delete` | Delete Product |
| `/report` | Product Report |

## Project Status
Currently in development.

## Future Improvements
- Invoice generation
- Customer management
- Improved security
- Additional billing reports