Event-Centric Social Media Application

Introduction

Welcome to the Event-Centric Social Media Application, a unique platform designed to bridge the gap between social networking and event management. In an era where digital connection is more significant than ever, our application stands out by offering users an innovative way to discover, organize, and engage with various events.

This social media application is tailored to enhance user experience in the realm of events. Whether it's local concerts, community workshops, or private gatherings, our platform makes it easy for users to create, share, and explore events. Alongside event management, the application incorporates familiar social media features such as posting updates, liking and commenting on posts, and following other users, thereby fostering a vibrant online community centered around events.

Objective

Our primary objective is to develop a fully functional REST API for this social media application using Java Spring Boot. This API will enable users to:

Create and manage events, complete with details like name, description, category, and images.
Post updates, engage with other users' posts through likes and comments.
Follow other users and cultivate a following, creating a dynamic and interactive community.
Indicate their attendance status for events (attending, not attending, or maybe) and add events to their favorites list.
Scope

The project is dedicated to backend API development utilizing Java Spring Boot and effective data management with MySQL. Key features include:

Robust user authentication and profile management.
Comprehensive event creation, categorization, and attendance management.
Interactive post creation, with support for likes and comments.
User relationship features, enabling followers/following dynamics and event bookmarking.`

Requirements

Before you begin working with the Event-Centric Social Media Application's API, ensure that your system is equipped with the following prerequisites:

Java SDK 17: The API is developed with Java 17. Download it from the Oracle website or use a package manager like Homebrew for macOS or apt for Ubuntu.
MySQL Database via XAMPP: The application leverages MySQL for data storage. XAMPP is recommended for its simplicity and includes MySQL. Download XAMPP from here and follow its installation guide for your operating system.
Gradle: This project uses Gradle as its build tool. Ensure you have Gradle installed for managing dependencies and running the project. Download and installation instructions can be found on the Gradle website.
Spring Boot Compatible IDE: An Integrated Development Environment (IDE) with support for Spring Boot and Gradle, such as IntelliJ IDEA, Eclipse, or Spring Tool Suite, is required. These IDEs typically come with embedded Gradle support.
Git: For version control and to clone the repository, Git is necessary. Install Git from Git SCM.
Insomnia: To test the API endpoints effectively, use Insomnia as the API client. While not mandatory for running the API, it is crucial for development and testing. Download Insomnia from here.

Installation and Setup

To set up and run the Event-Centric Social Media Application API on your local machine, follow these steps:

Cloning the Repository
Clone the Repository:
In IntelliJ IDEA (or your preferred IDE with Gradle support), use the built-in functionality to clone a project from Version Control by entering the Git repository URL.
Once cloned, IntelliJ IDEA will automatically configure the project.
Setting Up the Database
Database Setup:
Ensure MySQL is installed and running on your system through XAMPP. Start the MySQL module from the XAMPP Control Panel.
Open your MySQL database interface (like phpMyAdmin or MySQL Workbench).
Create a new empty database for the application. Note the database name, as you will need to configure it in the application properties.
Configuring the Application
Configure Application Properties:
Navigate to src/main/resources/application.properties in the project.
Update the database URL, username, and password to match your MySQL configuration. Make sure the database URL points to the empty database you just created.
Review and adjust other configurations if necessary to suit your environment.
Running the Application
Run the Application:
Find the main method in the Application class (typically Application.java or a similar name) in IntelliJ IDEA.
Right-click on the file and select 'Run' to start the application.
The Spring Boot application will initiate, automatically creating the required tables and schema in the specified database. You can now interact with the API on your local machine.


Usage

Below are examples of some common operations you can perform with the Event-Centric Social Media Application API. These examples can be tested using any API client, such as Insomnia, Postman, or even command-line tools like curl.

GET ALL EVENTS

<img width="1144" alt="Screenshot 2024-02-05 at 21 26 59" src="https://github.com/DeaMulladauti30028/EventApi/assets/150683059/a9b6c574-c8dc-4957-8fa0-ceaf9a80ae63">
 GET USER BY ID
<img width="1144" alt="Screenshot 2024-02-05 at 21 26 44" src="https://github.com/DeaMulladauti30028/EventApi/assets/150683059/370eb905-1d53-4daf-98f1-b6a1a7bef18a">
CREATE USER
<img width="568" alt="Screenshot 2024-02-05 at 21 26 12" src="https://github.com/DeaMulladauti30028/EventApi/assets/150683059/5c6fb884-7eb2-4e98-af46-f1115018a73a">
<img width="568" alt="Screenshot 2024-02-05 at 21 26 01" src="https://github.com/DeaMulladauti30028/EventApi/assets/150683059/2b5ce748-5336-4706-8217-bdbc8e363e5d">

Unit Testing

Unit testing is an integral part of the development process for the Event-Centric Social Media Application. It ensures that individual components of the application function as expected and helps in maintaining code quality. The application uses JUnit and Mockito for unit testing, focusing on the service layer, where the business logic resides.

Running the Tests
To run the existing unit tests:

Using an IDE:
In your IDE (like IntelliJ IDEA), navigate to the src/test/java directory.
Right-click on this directory and select 'Run Tests' (the option name may vary depending on your IDE).

Project Structure

The Event-Centric Social Media Application is organized into several key directories, each serving a specific purpose in the application's architecture. Here's a breakdown of the main folders and what they contain:

Main Application Source Code (src/main/java)
controller: Contains the controller classes that manage HTTP request and response handling. These classes act as the entry points for API calls.
pojo/entity: Houses the entity classes. These classes represent the application's data models and are mapped to the database tables.
pojo/dto: Includes Data Transfer Object (DTO) classes. DTOs are used to transfer data between layers and often represent a subset of entity data for requests and responses.
service: Holds the service classes where the core business logic of the application is implemented. These classes are responsible for data processing and business rule enforcement.
repository: Contains the repository interfaces that facilitate data access and manipulation by interacting with the database.
exceptions: Custom exception classes that handle specific error scenarios within the application, improving error management and responses.

Resources (src/main/resources)
application.properties: This file contains the application's configuration settings, including database connection details, framework-specific configurations, and custom settings.
Other resources: This folder can also include additional resources needed by the application, such as SQL scripts or XML files.

Test Code (src/test/java)
controller, service, repository: Each of these directories contains unit tests corresponding to their counterparts in the main application source code. These tests ensure that individual components function as expected.
resources: Includes resources needed specifically for testing, such as test-specific application properties or configuration files.

Test Resources (src/test/resources)
application.properties: Contains configuration settings for the testing environment, ensuring that tests run with the appropriate settings independent of the main application.
