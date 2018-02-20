FROM alopcas4wits/jdk8:1.0
MAINTAINER Jonas Holtorf <Jonas.Holtorf@4wits.de>

RUN apt-get update\
&& apt-get install -y gtk2-engines libxtst6 libxxf86vm1 freeglut3 libxslt1.1 xvfb libxrender1 libxi6 iputils-ping

ADD ./src/main/resources/startup.sh /app/startup.sh
ADD ./src/main/resources/css /app/css/
ADD ./target/app.jar /app/app.jar

ENTRYPOINT sh /app/startup.sh

EXPOSE 8080
