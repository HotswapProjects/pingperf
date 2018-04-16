#!/bin/sh
mvn clean package && docker build -f Dockerfile.meecrowave -t pingperf-meecrowave .
