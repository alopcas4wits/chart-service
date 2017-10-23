FROM alopcas4wits/jdk8-dev:1.0
MAINTAINER Jonas Holtorf <Jonas.Holtorf@4wits.de>

RUN apt-get update\
&& apt-get install -y gtk2-engines libxtst6 libxxf86vm1 freeglut3 libxslt1.1 xvfb libxrender1 libxi6 iputils-ping

WORKDIR /app

ADD src/main/resources/startup-dev.sh /app/startup-dev.sh

#CMD ["mvn","-Pdev","-Dfork=true", "-Drun.jvmArguments=-Xmx128m -Xms64m -Xdebug server=y,suspend=n,address=7000", "spring-boot:run", ">","~/log.txt"]
ENTRYPOINT sh /app/src/main/resources/startup-dev.sh
EXPOSE 8080
EXPOSE 7000
