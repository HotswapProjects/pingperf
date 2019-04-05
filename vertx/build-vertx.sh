#!/bin/sh
mvn package  && docker build -f Dockerfile.vertx -t pingperf-vertx .
