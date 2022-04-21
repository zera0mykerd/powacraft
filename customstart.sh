#!/bin/bash
cd $PWD
HOME=$PWD

#Example start script to run minecraft server with Java binaries portable.

screen -S PowaCraft $PWD/Java/bin/java -Xms1024M -Xmx1024M -XX:+UseG1GC -XX:+UnlockExperimentalVMOptions -XX:MaxGCPauseMillis=100 -XX:+DisableExplicitGC -XX:TargetSurvivorRatio=90 -XX:G1NewSizePercent=50 -XX:G1MaxNewSizePercent=80 -XX:G1MixedGCLiveThresholdPercent=50 -XX:+AlwaysPreTouch -jar stellarspigot.jar
