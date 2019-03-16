#!/bin/sh
mvn package -Pnative -Dnative-image.docker-build=true && docker build -f Dockerfile.quarkus-native -t pingperf-quarkus-native .

