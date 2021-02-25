FROM maven:3.6.3-openjdk-15-slim AS build
WORKDIR /build

COPY . .

RUN mvn -f pom.xml clean package 

# Release Image
FROM adoptopenjdk:15.0.2_7-jre-hotspot-focal AS release
COPY --from=build /build/target/*.jar /app.jar

COPY docker-entrypoint.sh /
RUN chmod +x /docker-entrypoint.sh
ENTRYPOINT ["/docker-entrypoint.sh"]
