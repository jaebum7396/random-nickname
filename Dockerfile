FROM openjdk:11
ARG JAR_FILE=./build/libs/random-nickname-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app.jar"]