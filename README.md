
# SINTA Application

SINTA is a web app-based tour package sales marketplace that focuses on providing convenience to travel agents in digitizing their business to reach a wider market.


## Installation with Docker

Install SINTA with Docker

1. Make application-docker.properties file on /resource directory
2. Copy all application.properties.example key-value into your application-docker.properties
3. Fill all credentials on application-docker.properties
4. Run ```docker-compose up -d```
    
## Installation with Local
1. Make sure you have install Apache Maven
2. Make application.properties /resource directory
3. Copy all application.properties.example key-value into your application.properties file
4. Fill all credentials on application.properties
5. Run ```mvn clean package -DskipTests=true``
6. Then run ```mvn spring-boot:run```
## Tech Stack
- [Java 17](https://www.java.com/en/)
- [PostgreSQL](https://www.postgresql.org/)
- [Spring Boot 3](https://spring.io/projects/spring-boot/)
- [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Spring Security](https://spring.io/projects/spring-security)
- [Hibernate](https://hibernate.org/)
- [Docker](https://www.docker.com/)
- [JavaMailSender](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/mail/javamail/JavaMailSender.html)
- [MapStruct](https://mapstruct.org/)
- [Lombok](https://projectlombok.org/features/)
- [Cloudinary](https://cloudinary.com/)
## Support

Don't forget to star my repository if you feel this repository help you on learning :)

