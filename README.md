# beefstar
E-commerce app 
[![BeefStar ecommerce](https://img.youtube.com/vi/F8M4EB00nqA/0.jpg)](https://www.youtube.com/watch?v=F8M4EB00nqA)

Application written in java using technologies such as Spring Boot, PostgreSql. The visual layer was written in Angular, The application is designed to run in Docker. 

To run the application you need to have docker environment installed, for Windows, installation guide is available at the link https://docs.docker.com/desktop/install/windows-install/ 

# Beefstar Application

## Installation

1. **Clone the repository:**
    ```bash
    git clone <repository_link>
    ```

2. **Build the JAR:**
    ```bash
    ./gradlew build
    ```

3. **Build the Docker image:**
    ```bash
    docker build -t beefstar .
    ```

4. **Run the container:**
    ```bash
    docker-compose up -d
    ```

After completing these steps, the Beefstar application will be available at `localhost:4200`.


## Roles and User Accounts

### Admin Role

- **Username:** `AdminBeefStar`
- **Password:** `admin@pass`

Admins have the following capabilities:

- Add new products to the system.
- Edit existing product details.
- Accept orders placed by users.
- Issue invoices for completed orders.

### User Role

To access user functionalities, you need to register an account.

Users have the following capabilities:

- Add products to their shopping cart.
- Place orders for selected products.
- Download invoices for completed orders.

Program uses spring security and jwt token mechanism, Hibernate and hibernate cache'ing to optimize database queries, external api to generate invoice -yakpdf
