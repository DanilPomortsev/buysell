FROM openjdk:19

EXPOSE 8080

EXPOSE 5432

ADD target/buysell.jar buysell.jar

ENTRYPOINT ["java", "-jar", "buysell.jar"]