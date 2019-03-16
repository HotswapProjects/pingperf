#!/bin/sh
docker run -ti --rm -p 8080:8080 -p 9990:9990 --network host pingperf-wildfly-hollow-swarm

