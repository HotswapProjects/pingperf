#!/bin/sh
mvn clean package && docker build -f Dockerfile.openliberty-micro -t pingperf-openliberty-micro .
