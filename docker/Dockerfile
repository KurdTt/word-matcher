FROM openjdk:17-alpine

RUN mkdir /app
COPY word-matcher.jar /app/word-matcher.jar

ENTRYPOINT ["java","-jar","/app/word-matcher.jar"]