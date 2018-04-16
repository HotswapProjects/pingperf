     (                      (
     )\ )                   )\ )            (
    (()/( (          (  (  (()/(   (   (    )\ )
     /(_)))\   (     )\))(  /(_)) ))\  )(  (()/(
    (_)) ((_)  )\ ) ((_))\ (_))  /((_)(()\  /(_))
    | _ \ (_) _(_/(  (()(_)| _ \(_))   ((_)(_) _|
    |  _/ | || ' \))/ _` | |  _// -_) | '_| |  _|
    |_|   |_||_||_| \__, | |_|  \___| |_|   |_|
                    |___/

# Performance tests of java [MicroProfiles](https://microprofile.io/) and Spring-Boot

## Motivation

Docker based comparision of java MicroProfiles and Spring-Boot using simple ping REST request. Docker images are based
on images from [hotswap-docklands](https://github.com/HotswapProjects/hotswap-docklands). Spring-Boot test application 
is available at [Pingperf-spring-boot](https://github.com/HotswapProjects/pingperf-spring-boot). 

## Quick start

* run `build-X.sh` to build docker image for given microprofile or app server
* send REST requests as fast as possible (in single thread) : `./perf-utils/echo_test.py`
* monitor performance : `watch "docker stats --no-stream --no-trunc && echo && ./perf-utils/ping_statistics.py"`
* for more accurate measurement use [jmeter](https://github.com/HotswapProjects/pingperf/jmeter_test.jmx)

## Results

Load tests are currently using 3 sets varying in heap size and number of users (clients):

- Xmx128m running single user (client)
- Xmx256m running 10 users (clients)
- Xmx256 running 50 users (clients)

Following  were measured by JMeter.

### Single thread (user) / Xmx128m

|Microprofile|Start time|Docker mem usage|Throughput req/s|JAVA_OPTS|
|------------|----------|----------------|----------------|---------|
|Meecrowave 1.2.1|0.9s|163MB|3850/s|-Xmx128m|
|Open Liberty 17.04|4s|259MB|3260/s|-Xmx128m|
|Payara Micro 5.181|5s|312MB|3433/s|-Xmx128m --nocluster|
|Wildfly Swarm 2018.3.3|5s|347MB|3158/s|-Xmx128m|
|Spring-Boot 2.0.1|1.9s|257MB|4113/s|-Xmx128m|

### 10 threads (users) / Xmx256m

|Microprofile|Start time|Docker mem usage|Throughput req/s|JAVA_OPTS|
|------------|----------|----------------|----------------|---------|
|Meecrowave 1.2.1|0.9s|207MB|25446/s|-Xmx256m|
|Open Liberty 17.04|4s|290MB|33240/s|-Xmx256m|
|Payara Micro 5.181|5s|352MB|25798/s|-Xmx256m --nocluster|
|Wildfly Swarm 2018.3.3|5s|390MB|22897/s|-Xmx256m|
|Spring-Boot 2.0.1|1.9s|276MB|26559/s|-Xmx256m|

### 50 threads (users) / Xmx 256

|Microprofile|Start time|Docker mem usage|Throughput req/s|JAVA_OPTS|
|------------|----------|----------------|----------------|---------|
|Meecrowave 1.2.1|0.9s|237MB|33472/s|-Xmx256m|
|Open Liberty 17.04|4s|290MB|44194/s|-Xmx256m|
|Payara Micro 5.181|5s|386MB|36042/s|-Xmx256m --nocluster|
|Wildfly Swarm 2018.3.3|5s|413MB|40889/s|-Xmx256m|
|Spring-Boot 2.0.1|1.9s|276MB|34814/s|-Xmx256m|

## RaspberryPI

Motivation of this test is to check ability to run MicroProfile application on RaspberyPi3.

|Microprofile|Start time|Mem usage|Throughput req/s|JAVA_OPTS|
|------------|----------|----------------|----------------|---------|
|Meecrowave 1.2.0|9s||250/s||
