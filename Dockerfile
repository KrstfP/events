# Stage 1: Build
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/*.jar app.jar

ENV DB_URL="jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=PostgreSQL"
ENV DB_USER=sa
ENV DB_PASSWORD=sa
ENV DB_DIALECT=org.hibernate.dialect.H2Dialect

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]