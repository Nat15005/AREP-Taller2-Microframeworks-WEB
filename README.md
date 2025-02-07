# Web Framework Development for REST Services and Static File Management

This framework allows developers to define REST services using lambda functions, manage query values from requests, and specify the location of static files. The goal is to provide a robust and scalable framework for building web applications with backend services and static file handling.

As a demonstration of the server's functionality, a simple web application is included to handle books. It allows users to add, delete, and list books using REST services.

## Getting Started

These instructions will guide you to get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

To run this project, you need the following software installed on your local machine:

- **Java 21+**: The project is built using Java. 
- **Maven**: Used for dependency management.
- **IDE (optional)**: An Integrated Development Environment like IntelliJ IDEA can be used for development.

### Installing

Follow these steps to get the development environment running:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Nat15005/AREP-Taller2-Microframeworks-WEB.git
   ```
2. **Navigate to the project folder:**
   ```bash
   cd AREP-Taller2-Microframeworks-WEB
   ```
3. **Build the project using Maven:**
   ```bash
   mvn clean install
   ```

### Running the Server

Once the project is built, you can start the server with the following command:

```bash
java -cp target/classes edu.escuelaing.arep.HttpServer
```

The server will start and listen on port `35000`.

### Accessing the Application

Open your web browser and go to:

```
http://localhost:35000/
```

You should see the main page of the application.

![image](https://github.com/user-attachments/assets/ee733dbf-a387-4ed7-b243-9d8bdeaf2666)

![image](https://github.com/user-attachments/assets/f2382c33-b777-4073-8d8f-9a355a512263)

### Example of GET REST Services
To test the functionality of the new REST framework, try the following endpoints in your browser:

- GET request with query parameters:
  
```  
http://localhost:35000/App/hello?name=Natalia
```

This will return: Hello Natalia

![image](https://github.com/user-attachments/assets/1c2664e9-ae19-40e6-baf2-c772b0077842)


- GET request with predefined response:
  
```  
http://localhost:35000/App/pi
```
This will return the value of Pi: 3.141592653589793

![image](https://github.com/user-attachments/assets/84b2226c-674c-4105-968f-376323dffbe9)

### Static File Location Specification

The framework includes a staticfiles() method that allows developers to define where the static files (like images, CSS, and HTML) are located. By default, this method looks for static files in the /static folder. 

Once the staticfiles() method is configured, you can easily access static resources like CSS, PNG, and other files by simply making a request to the corresponding URL. For example, a request to:

```  
http://localhost:35000/index.css

```
![image](https://github.com/user-attachments/assets/b2592241-c83d-42f6-9443-05fb27035a8c)

This will return the index.css file, and:

```  
http://localhost:35000/pato.png

```
This will serve the pato.png image from the static folder.

![image](https://github.com/user-attachments/assets/d32c91ef-8457-48fc-8226-ef4a9f60f52a)

#### Changing the Static File Location

You can change where the framework looks for static files by configuring the staticfiles() method to point to a different folder. For example, if you have a test folder called prueba, you can update the configuration as follows:

![image](https://github.com/user-attachments/assets/45e7297e-4c58-4ad4-abf2-b48dcd5f9912)

Once this change is made, the server will search for static files in the prueba directory. This means that any requests for static resources will now be handled by files located inside prueba. For example:

```  
http://localhost:35000/index.html

```
This will serve the index.html file from the prueba folder.
![image](https://github.com/user-attachments/assets/ef2cf31a-8dd1-4126-9a53-0f6430a8db91)


### Running Tests

To run the unit tests, use the following command:

```bash
mvn test
```
![image](https://github.com/user-attachments/assets/cc10c5a0-3515-4140-9c7c-01e8b8f2c805)



### Project Structure

```
AREP-Taller-1
├───src
│   ├───main
│   │   ├───java
│   │   │   └───edu
│   │   │       └───escuelaing
│   │   │           └───arep
│   │   │                   Book.java
│   │   │                   FileHandler.java
│   │   │                   HttpServer.java
│   │   │                   Request.java
│   │   │                   RequestHandler.java
│   │   │                   Response.java
│   │   │                   WebFramework.java
│   │   │
│   │   └───resources
│   │       ├───prueba
│   │       │       index.html
│   │       │
│   │       └───static
│   │               fondo.jpg
│   │               index.css
│   │               index.html
│   │               index.js
│   │               pato.png
```

### Technologies Used

- **Java** - Main programming language
- **Maven** - Dependency management and build tool
- **JUnit** - For unit testing
- **HTML, CSS, JavaScript** - Frontend components

### Author

Developed by **Natalia Rojas** https://github.com/Nat15005.

### Acknowledgments

- Java and Networking Documentation - For offering essential references on socket programming.

- Open Source Community - For tools and resources that helped in the development of this project.


