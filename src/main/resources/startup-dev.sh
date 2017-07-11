#!/bin/bash

touch /tmp/.X1-lock && rm /tmp/.X1-lock
Xvfb :1 -screen 0 1600x1200x16 &
export DISPLAY=:1.0
sleep 15
ps -aef | grep Xvfb
DISPLAY=:1.0 nohup mvn -Pdev -Dfork=true -Drun.jvmArguments="-Xmx128m -Xms64m" spring-boot:run
exit 0