#!/bin/sh
mvn clean package && docker build -f Dockerfile.meecrowave-nowar -t pingperf-meecrowave-nowar .
