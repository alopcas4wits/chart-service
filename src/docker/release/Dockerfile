FROM alopcas4wits/jdk8:1.0
MAINTAINER Jonas Holtorf <Jonas.Holtorf@4wits.de>

ADD ./src/main/resources/startup.sh /app/startup.sh
ADD ./target/app.jar /app/app.jar

ENTRYPOINT sh /app/startup.sh

EXPOSE 8080
