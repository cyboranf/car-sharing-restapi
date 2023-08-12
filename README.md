# Car-Sharing-REST API

This project is a REST API designed for car rental services. Developed with Spring Boot, it encompasses several features to ensure a smooth car-sharing experience for all.

## Features
- **User Authentication**: Uses Spring Security integrated with JWT.
- **Car Rentals**: Rent from others or list your own car.
- **Chat System**: Directly chat with car owners about offers.
- **Reviews and Ratings**: Share your experience and read about others'.

## Default Credentials
| Role         | First name| Password |
|--------------|----------|----------|
|     User     |   John   | Password1     |
| SHARING_USER | Jane  | Password1  |
|   CAR_HIRE   | Alice  | Password1  |
|    Admin     | Bob    | Password1    |

## Inspiration
The initial thought wasn't just to create another car-sharing application. The vision was broader: car-sharing for everyone. Whether you're an individual looking to monetize your vehicle's idle time or a company wanting to put your fleet for hire, this platform caters to all. You can rent a car from anyone you desire, be it a next-door neighbor with a spare sedan or a business with a vast array of luxury vehicles. The power of choice is in your hands.


## Database Design
The project relies on a MySQL database. The Entity Relationship Diagram (ERD) gives a holistic view of the schema. 

![ERD](/images/erd.png)

It's a vast database design aiming to replicate a complex system for the Car-Sharing application, primarily to delve deep into the nuances of REST-API architecture.

## Integrations
The project integrates with:
- PayPal API for payment functionalities.
- Google's Calendar and Gmail for scheduling and communication respectively.

## Tech Stack
- **Spring Boot**: v.2.5.9
- **Java**: v.17
- **Spring JPA**
- **Spring Security**: With JWT
- **Mockito**
- **JUnit**
- **MySQL**

## Documentation
A comprehensive documentation using Swagger provides an in-depth guide to the API endpoints and their responses.
![Swagger Documentation](/images/documentation.png)

## Future Plans
In the future i want to do a Front-end for this application, so i did an Admin panel as well, because it will give me a lot of space to practice new technologies and to build upon the existing features in my app. 

## Getting Started

1. Clone the repository to your local machine.
2. Make sure you have Java 11 and Maven installed.
3. Update the `application.properties` file with your database credentials.
4. Run `mvn clean install` to build the application.
5. Start the application with `mvn spring-boot:run`.

The API will be accessible at `http://localhost:8080`.

## Contributing

Contributions are welcome. Please feel free to submit a pull request or open an issue.

---

*For any queries or feedback, please reach out to [cyboranf@gmail.com](mailto:cyboranf@gmail.com)*
