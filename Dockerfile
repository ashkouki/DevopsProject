FROM openjdk:8
ADD target/DevopsGomyCode.jar DevopsGomyCode.jar
ENTRYPOINT ["java", "-jar" , "DevopsGomyCode.jar"]

