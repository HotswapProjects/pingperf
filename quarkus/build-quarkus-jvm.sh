#!/bin/sh
mvn package  && docker build -f Dockerfile.quarkus-jvm -t pingperf-quarkus-jvm .
