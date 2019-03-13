#!/bin/sh
docker run -ti --rm -p 8080:9080 --network host pingperf-openliberty-micro
