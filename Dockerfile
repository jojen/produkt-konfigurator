FROM java:openjdk-8-jre
MAINTAINER jochen.gaiser@schmalz.de

#Spring Boot
CMD java -jar pk-1.0.jar
ADD /target/pk-1.0.jar pk-1.0.jar