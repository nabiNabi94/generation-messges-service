FROM openjdk:11
WORKDIR /code
COPY . /code
VOLUME /tmp
EXPOSE 8080
ADD pom.xml /project/pom.xml
ADD src /project/src
ADD target/*.jar generationapp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /code/generator.jar
ENTRYPOINT ["java","-jar","generationapp"]