E-Commerce Application
This project is a Spring Boot-based e-commerce application that provides APIs for managing products and categories, including features like pagination, sorting, custom queries, and integration with MongoDB for additional data storage. It also includes monitoring capabilities using Spring Actuator and custom configurations for the DispatcherServlet.
Features
Product and Category Management:
Add, update, delete, and retrieve products and categories.
Products are linked to categories using a @ManyToOne relationship.
Pagination and Sorting:
Retrieve paginated and sorted lists of products.
Supports dynamic page size, page number, and sorting parameters via query parameters.
Custom Queries:
JPQL and native SQL queries for advanced data retrieval.
Example: Find products by category name or count products in a specific category.
MongoDB Integration:
Stores product reviews in a NoSQL database (MongoDB).
Provides APIs to add and retrieve reviews for products.
Monitoring with Spring Actuator:
Exposes endpoints for application health checks (/actuator/health), metrics (/actuator/metrics), and more.
Custom DispatcherServlet Configuration:
Routes all API requests through /api/*.
Supports custom exception handling and request interceptors.
Error Handling:
Global exception handling with meaningful error messages.
Handles serialization issues like circular references between Product and Category.
Technologies Used
Spring Boot: Framework for building the application.
Spring Data JPA: For interacting with the relational database.
Spring Data MongoDB: For managing NoSQL data storage.
Spring Actuator: For monitoring and management endpoints.
Hibernate: ORM tool for database interactions.
Jackson: For JSON serialization/deserialization.
Lombok: To reduce boilerplate code (e.g., getters/setters).
H2 Database (In-Memory): For development/testing purposes (can be replaced with MySQL or PostgreSQL).
Postman: For testing APIs.