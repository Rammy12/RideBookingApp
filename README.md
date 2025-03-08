# RideBookingApp

A comprehensive ride-booking application built using Java and Spring Boot, designed to facilitate seamless ride-hailing services with user authentication, booking management, and real-time tracking.

## 🚀 Features

### 1. **User Authentication & Authorization**
   - Secure user registration and login using JWT.
   - Role-based access control (ADMIN, DRIVER, RIDER).
   - Password hashing using BCrypt for security.

### 2. **Ride Booking & Management**
   - Passengers can book rides by entering their pickup and drop-off locations.
   - Drivers receive ride requests and can accept or decline them.
   - Ride history and status tracking (Pending, Accepted, Completed, Canceled).

### 5. **Driver & Vehicle Management**
   - Drivers can register and add vehicle details.
   - Verification process for drivers before activation.
   - Rating and feedback system for drivers.

### 6. **Admin Dashboard**
   - Manage users, drivers, and ride transactions.
   - Monitor ride requests, active rides, and revenue.

### 7. **Notification System**
   - Real-time notifications for ride updates.
   - SMS and email alerts for booking confirmations and cancellations.

### 9. **Review & Ratings**
   - Passengers can rate drivers and provide feedback.
   - Driver ratings affect ride matching algorithm.
     
### 10. **Wallet Feature**
   - Users can add money to their wallet for seamless transactions.
   - Wallet balance can be used for ride payments.
   - Secure top-up and withdrawal options.
   - Transaction history tracking for transparency.

## 🛠 Technologies Used
- Java 17+
- Spring Boot
- Spring Security & JWT
- Hibernate & JPA
- PostgreSQL ( PostGIS )
- OSRM ( for distance calculation )
- Maven

## 📂 Project Structure
```
RideBookingApp/
│-- src/main/java/com/example/ridebooking
│   │-- controller/  # API Controllers
│   │-- service/     # Business logic layer
│   │-- repository/  # Database access layer
│   │-- entities/       # Entity classes
│   │-- security/    # JWT and authentication logic
│   │-- config/      # Application configurations
│   └-- RideBookingAppApplication.java # Main application
│-- src/main/resources/
│   └-- application.properties  # Configuration file
│-- pom.xml  # Maven dependencies
```

## ⚙️ Setup and Installation
### Prerequisites
- Java 17+
- Maven
- PostgreSQL/MySQL
- Google Maps API Key (for real-time location tracking)
- Stripe/Razorpay API Key (for payments)

### Steps to Run the Project
1. **Clone the repository:**
   ```sh
   git clone https://github.com/Rammy12/RideBookingApp.git
   cd RideBookingApp
   ```

2. **Configure Database:**
   Update `src/main/resources/application.properties` with your database credentials:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/ride_booking_db
   spring.datasource.username=your_db_username
   spring.datasource.password=your_db_password
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Build and Run the Project:**
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access API Endpoints:**
   - Register User: `POST /auth/signup`
   - Login User: `POST /auth/login`
   - OnBoard Driver ( ADMIN Authenticated ) : `POST /auth/onBoardNewDriver/userId`
   - Request Ride (RIDER Authenticated ): `POST /drivers/acceptRide/rideRequestId`
   - Cancel Ride (RIDER Authenticated ): `POST /drivers/cancelRide/rideRequestId`
   - Rate Driver (RIDER Authenticated ): `POST /drivers/rateDriver`
   - Get My Rides (RIDER Authenticated ): `GET /drivers/getMyRides`
   - Accept Ride (DRIVER Authenticated ): `POST /drivers/acceptRide/rideRequestId`
   - Start Ride (DRIVER Authenticated ): `POST /drivers/startRide/rideRequestId`
   - End Ride (DRIVER Authenticated ): `POST /drivers/endRide/rideId`
   - Cancel Ride (DRIVER Authenticated ): `POST /drivers/cancelRide/rideId`
   - Get My Rides (DRIVER Authenticated ): `GET /drivers/getMyRides`
   - Get Ride Status: `GET /rides/{rideId}`

## 🤝 Contributing
Contributions are welcome! Feel free to open issues or submit pull requests.

## 📧 Contact
For any questions, contact **[Ramesh kumar]** at `rameshkumar455555@gmail.com`.

