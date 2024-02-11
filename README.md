# Salon Management System
This project, developed as part of my Java programming course, serves as a comprehensive salon management system designed to streamline operations and enhance customer service.

Overview
The Salon Management System provides a user-friendly interface for salon administrators to manage appointments. Built with security in mind, access to the application is restricted to users with administrative privileges, ensuring data integrity and confidentiality.

Functionality
Staff Management: Administrators can add manicurists to the system, along with their relevant details such as name, surname, phone number, and date of employment.
Customer Management: The system allows for the management of customer records, including name, surname, date of birth, email address, and status.
Service Management: Administrators can define salon services, specifying the name and price for each service.
Appointment Booking: Users can schedule appointments by selecting the desired date and time, along with the manicurist and services to be performed.
CRUD Operations: The system supports basic CRUD (Create, Read, Update, Delete) operations for all entities, enabling administrators to maintain accurate and up-to-date records.

Technical Details
Authentication: Access to the application is restricted to users with administrative roles, and authentication is managed through the Spring Boot Security framework.
Database: Data persistence is achieved using MySQL relational database, allowing for efficient storage and retrieval of salon-related information.
User Interface: The application features an intuitive user interface developed using Spring MVC with Thymeleaf.
