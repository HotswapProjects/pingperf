#!/bin/sh
mvn clean package && docker build -f Dockerfile.payara-micro -t pingperf-payara-micro .
