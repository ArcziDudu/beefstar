# beefstar
E-commerce app 
Application written in java using technologies such as Spring Boot, PostgreSql. The visual layer was written in Angular, The application is designed to run in Docker. 

To run the application you need to have docker environment installed, for Windows, installation guide is available at the link https://docs.docker.com/desktop/install/windows-install/ 

next steps: 
download the repository: git clone <link>.
build jar: ./gradlew build
build image: docker build -t beefstar .
running the container: docker compose up -d

the application will be available at link localhost:4200

The application has two roles: admin 
username: AdminBeefStar
password: admin@pass
Admin can: add product, edit products, accept order and issue invoice.

User role - you need to register:
User can, add products to cart, order products, download invoices 

Program uses spring security and jwt token mechanism, Hibernate and hibernate cache'ing to optimize database queries, external api to generate invoice -yakpdf
