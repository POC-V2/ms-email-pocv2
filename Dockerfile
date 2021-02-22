FROM maven:3.6.3-openjdk-15-slim AS build
WORKDIR /build

COPY . .

RUN mvn -f pom.xml clean package 

# Release Image
FROM openjdk:15-jdk AS release
COPY --from=build /build/target/*.jar /app.jar

COPY docker-entrypoint.sh /
RUN chmod +x /docker-entrypoint.sh
ENTRYPOINT ["/docker-entrypoint.sh"]
