# 🛒 E-commerce API

This is an **E-commerce API** developed using **Spring Boot** and **Java**, providing core functionalities for managing users, products, orders, and authentication with **JWT (JSON Web Token)**.

## 🚀 Features

- User registration with roles (`USER` and `ADMIN`)
- Token-based authentication using JWT
- Product CRUD operations (restricted to `ADMIN`)
- Order creation linked to authenticated users
- Retrieve orders and order items by authenticated user
- Role-based access control with Spring Security

## 🧠 Technologies

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT (Json Web Token)
- Hibernate
- MySQL

## 🔐 Security

The system uses **JWT authentication** and **Spring Security** for role-based access control:

- `ADMIN`: Can manage all products and view all orders
- `USER`: Can create orders and view only their own

## 🛠️ Main Endpoints

### Authentication
- `POST /auth/register` → Register a new user
- `POST /auth/login` → Get JWT token

### Products
- `GET /products` → List all products
- `POST /products` → Create a product (ADMIN only)
- `PUT /products/{id}` → Update a product (ADMIN only)
- `DELETE /products/{id}` → Delete a product (ADMIN only)

### Orders
- `POST /orders` → Create an empty order
- `PUT /orders/{id}/items` → Add items to an existing order
- `GET /orders/{id}` → Get order details

## 🧪 Testing

You can use **Insomnia** or **Postman** to test the API endpoints. Don’t forget to authenticate using a JWT token to access protected resources.

### ⚠️ Disclaimer
This project was created for learning purposes
