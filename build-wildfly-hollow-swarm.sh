#!/bin/sh
mvn clean package && docker build -f Dockerfile.wildfly-hollow-swarm -t pingperf-wildfly-hollow-swarm .
