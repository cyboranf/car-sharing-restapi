# Car Sharing REST API 

This project is a REST API designed for car rental services, named "Car Sharing". It is built using Spring Boot and provides various features, such as user authentication, car booking, car features management and more.

## ERD

![ERD](/images/erd.png)

## Swagger Documentation

![Swagger Documentation](/images/documentation.png)

## Features

- User Authentication: The API provides secure registration and login features for users.
- Car Booking: Users can book cars available for rent.
- Car Management: Administrators can add new cars, update car details and remove cars from the system.
- Reviews and Ratings: Users can rate and review the cars they rented.

## Tech Stack

- Spring Boot
- Spring Security
- Hibernate
- MySQL

## Getting Started

1. Clone the repository to your local machine.
2. Make sure you have Java 11 and Maven installed.
3. Update the `application.properties` file with your database credentials.
4. Run `mvn clean install` to build the application.
5. Start the application with `mvn spring-boot:run`.

The API will be accessible at `http://localhost:8080`.

## Contributing

Contributions are welcome. Please feel free to submit a pull request or open an issue.
