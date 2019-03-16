#!/bin/sh
docker run -ti --rm -p 8080:8080 --network host pingperf-quarkus-native
