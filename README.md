# Digital Wallet Backend Application 

This digital wallet application is built on the Spring Boot framework and utilizes MySQL as the database backend. It empowers users to effortlessly create and manage their digital wallets, securely add funds to it, and make transactions. Additionally, the application seamlessly integrated with ClickPay, a reputable payment gateway provider located in Saudi Arabia.

> `Built with Spring Boot, secured with Spring Security (JWT), documented with Swagger (API),
containerized with Docker`

### The Digital Wallet Integrated with Click Pay (Payment Provide)
https://merchant.clickpay.com.sa/

### Application components
* Backend Services
   - Auth Endpoint
       * Login
       * Sign in
       * Activate Account
       * Forget password
       * Resend Token
   - Users Endpoint
       * Update Password
       * Logout
   - Payment Endpoint
       * Deposit Money 
       * Withdrawal, Bank transfer
       * Add Recipient
       * Wallet to Wallet transfer
       * Webhook, Callback
       * Query transaction
       * 3DS, Payment Link
       * Verviy transaction
       * Banks supported 
    - Notification
       * Google notification is enabled
    - Swagger
       * Digital Wallet API Documentation
    - Templates
       * Home
       * Registration
       * User-login
    - Doker
       * Docker-compose
       * Dockerfile
    - Caching
      * Memcached
        
### Project Setup
    Language: Java
    Build system: Maven
    JDK version: 8
    Spring boot version: 2.76
    Memcached
    Mysql

### Launch services in a container
* Ensure you have docker installed and running on the machine/server you want to run this service on.
* Simply download the [Docker compose file](https://github.com/mohsenTalal/Digital-Wallet-API/blob/main/docker-compose.yml).
* You can edit the file to your custom configurations.
* Then navigate to where the file is located on your terminal and just run 'docker-compose up.
* Finally! once the images downloads are completed and the application is running, you can then visit http://localhost:8083/swagger-ui/index.html to access the API end points.

### Launch services locally
* Ensure Memcached is installed and running on your machine before you run this service.
* **Clone the repository:** git clone https://github.com/mohsenTalal/Digital-Wallet-API.git.
* **Build the project using maven:** mvn clean install. 
* **Run the application:** mvn spring-boot:run.

### Usage
The application exposes a RESTful API for creating and managing digital wallets. 
Use the following link to access the exposed API doc when the application is running on your local machine:
http://localhost:8083/swagger-ui/index.html

### Test Credit Card information
Card Type                 | Card Number(s)     | CVV      | 3D enrolled
:-------------------------|:-------------------|:---------|:-----------
Visa                      | 4000000000000002   | 123      | Yes
MasterCard                | 5498383801606532   | 977      | Yes
			      

### Authentication and Authorization
Uses Spring Security with JWT for stateless authentication and authorization.

### Configuration
The application uses Mysql as the database. The server is configured to run on port 3307 which can be
changed in the application.properties file.

The database, email, and all configurations can be set in the application.properties file.

### Note
> For security purpose, please make sure to set appropriate access controls for Mysql.

### Deployment
The application can be deployed on any Java Servlet container or docker container.

# Support

Need help or wanna share your thoughts? Don't hesitate to join us on Gitter or ask your question on StackOverflow:

>  StackOverflow: https://stackexchange.com/users/13936221/abdul-mohsen-al-enazi


# Contributors

>  Digital-Wallet-API is actively maintained by **[Mohsen Talal](https://github.com/mohsenTalal)**. Contributions are welcome and can be submitted using pull requests.

