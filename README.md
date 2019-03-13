     (                      (
     )\ )                   )\ )            (
    (()/( (          (  (  (()/(   (   (    )\ )
     /(_)))\   (     )\))(  /(_)) ))\  )(  (()/(
    (_)) ((_)  )\ ) ((_))\ (_))  /((_)(()\  /(_))
    | _ \ (_) _(_/(  (()(_)| _ \(_))   ((_)(_) _|
    |  _/ | || ' \))/ _` | |  _// -_) | '_| |  _|
    |_|   |_||_||_| \__, | |_|  \___| |_|   |_|
                    |___/

# Performance tests of java [MicroProfiles](https://microprofile.io/), Spring-Boot and Quarkus

## Motivation

Docker based comparision of java MicroProfiles and Spring-Boot using simple ping REST request. Docker images are based
on images from [hotswap-docklands](https://github.com/HotswapProjects/hotswap-docklands). Spring-Boot test application 
is available at [Pingperf-spring-boot](https://github.com/HotswapProjects/pingperf-spring-boot). Quarkus test is available
at [Pingperf-Quarkus](https://github.com/HotswapProjects/pingperf-quarkus)

## Quick start

* run `build-X.sh` to build docker image for given microprofile
* run `run-X.sh` to run docker image for given microprofile
* run jmeter test with appropriate number of clients. e.g.[jmeter](https://github.com/HotswapProjects/pingperf/jmeter_50users.jmx) script.

## HW/OS
```
@skybber ~ $ cat /proc/cpuinfo
processor       : 0
vendor_id       : AuthenticAMD
cpu family      : 23
model           : 1
model name      : AMD Ryzen 5 1600 Six-Core Processor

@skybber ~ $ cat /proc/version 
Linux version 5.0.0-arch1-1-ARCH (builduser@heftig-18825) (gcc version 8.2.1 20181127 (GCC)) #1 SMP PREEMPT Mon Mar 4 14:11:43 UTC 2019
```

## JMeter load tests results

Tests were splitted into 3 groups varying in the heap size and the number of users (clients):

- Xmx128m running single client
- Xmx256 running 10 clients
- Xmx256 running 50 clients

### Single thread (user) / -Xmx128m

|Microprofile|Start time|Docker mem usage|Throughput req/s|JAVA_OPTS|
|------------|----------|----------------|----------------|---------|
|Meecrowave 1.2.7|0.7s|181MB|5567/s|-Xmx128m|
|Open Liberty 19.02|0,7s,real ~3s|247MB|3708/s|-Xmx128m|
|Payara Micro 5.191|6s|320MB|3116/s|-Xmx128m --nocluster|
|Wildfly Swarm 2018.3.3|5s|330MB|3256/s|-Xmx128m|
|Spring-Boot 2.1.1|1.4s|235MB|5970/s|-Xmx128m|
|Quarkus 0.11.00 |0.01s|||no|

### 50 threads (users) / -Xmx256m

|Microprofile|Start time|Docker mem usage|Throughput req/s|JAVA_OPTS|
|------------|----------|----------------|----------------|---------|
|Meecrowave 1.2.7|0.7s|252MB|33425/s|-Xmx256m|
|Open Liberty 19.0.02|4s|311MB|41669/s|-Xmx256m|
|Payara Micro 5.191|6s|403MB|28942/s|-Xmx256m --nocluster|
|Wildfly Swarm 2018.3.3|5s|404MB|39630/s|-Xmx256m|
|Spring-Boot 2.1.1|1.4s|280MB|35013/s|-Xmx256m|
|Quarkus 0.11.00 |0.01s|||no|

### 10 threads (users) / -Xmx256m

|Microprofile|Start time|Docker mem usage|Throughput req/s|JAVA_OPTS|
|------------|----------|----------------|----------------|---------|
|Meecrowave 1.2.4|0.7s|219MB|25121/s|-Xmx256m|
|Open Liberty 18.04|3s|298MB|33070/s|-Xmx256m|
|Payara Micro 5.184|6s|399MB|24768/s|-Xmx256m --nocluster|
|Wildfly Swarm 2018.3.3|5s|390MB|23311/s|-Xmx256m|
|Spring-Boot 2.1.1|1.4s|268MB|24908/s|-Xmx256m|

## RaspberryPI

Motivation of this test is to check ability to run MicroProfile application on RaspberyPi3.

|Microprofile|Start time|Mem usage|Throughput req/s|JAVA_OPTS|
|------------|----------|----------------|----------------|---------|
|Meecrowave 1.2.0|9s||250/s||
