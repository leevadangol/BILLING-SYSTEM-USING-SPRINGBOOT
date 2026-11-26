# Billing System - Spring Boot Web App

A Spring Boot (Thymeleaf) website version of the reference BillingSystem project.
It uses the **same database** (`billing_system`, tables `users` and `products`), the same
pages and the same validation rules.

Default login: **admin / admin123**

## Features
- Login / Logout (Spring Security, session based; SHA-256 password hashes stay compatible with the reference DB)
- Home dashboard
- Add Item, Update Item (select a row, edit), Delete Item (with confirmation)
- Product Report with total count
- Validation: name <= 100 chars, description <= 255 chars, quantity whole number > 0, price > 0 (max 2 decimals)

## Requirements
- JDK 17 or newer
- Maven 3.8+ (or open in IntelliJ / Eclipse / VS Code and run `BillingSystemApplication`)
- MySQL 8 (XAMPP MySQL also works)

## Run with MySQL
1. Start MySQL (XAMPP Control Panel).
2. Optional: import `database/billing_system.sql` in phpMyAdmin. If you skip it, the app creates
   the database/tables and the `admin` user automatically on first start.
3. If your MySQL root user has a password, edit `src/main/resources/application.properties`.
4. Run:
       mvn spring-boot:run
5. Open http://localhost:8080 and log in.

## Run without MySQL (quick test, data is lost on restart)
       mvn spring-boot:run -Dspring-boot.run.profiles=h2

## Project layout
    src/main/java/com/billing
      BillingSystemApplication.java
      config/       SecurityConfig, DataInitializer
      controller/   HomeController, ProductController
      model/        Product, AppUser, ProductForm (validation)
      repository/   ProductRepository, UserRepository
      security/     Sha256PasswordEncoder, DbUserDetailsService
    src/main/resources
      templates/    login, home, add_item, update_item, delete_item, report, fragments
      static/css/   style.css (copied from the reference project)
      application.properties, application-h2.properties

## Pages
| URL | Purpose |
|-----|---------|
| /login | Login |
| /home | Dashboard |
| /items/add | Add product |
| /items/update | Select and update product |
| /items/delete | Delete product |
| /report | Product report |

## Note
Passwords are SHA-256 (unsalted) only to stay compatible with the reference database.
For real use, switch to BCrypt: replace `Sha256PasswordEncoder` with `BCryptPasswordEncoder`
and re-create the user passwords.
