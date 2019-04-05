     (                      (
     )\ )                   )\ )            (
    (()/( (          (  (  (()/(   (   (    )\ )
     /(_)))\   (     )\))(  /(_)) ))\  )(  (()/(
    (_)) ((_)  )\ ) ((_))\ (_))  /((_)(()\  /(_))
    | _ \ (_) _(_/(  (()(_)| _ \(_))   ((_)(_) _|
    |  _/ | || ' \))/ _` | |  _// -_) | '_| |  _|
    |_|   |_||_||_| \__, | |_|  \___| |_|   |_|
                    |___/

# Performance tests of java [MicroProfiles](https://microprofile.io/), [Spring-Boot](https://spring.io/projects/spring-boot), [Quarkus](https://quarkus.io/) and [Micronaut](https://micronaut.io/)

## Motivation

Docker based comparision of java MicroProfiles and Spring-Boot using simple ping REST request. Docker images are based
on images from [hotswap-docklands](https://github.com/HotswapProjects/hotswap-docklands).
## Quick start

* run `build-X.sh` to build docker image for given microprofile, spring-boot, quarkus, micronaut
* run `run-X.sh` to run docker image for given microprofile, spring-boot, quarkus, micronaut
* run wrk test with appropriate number of clients.

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

## Wrk load tests results

Tests were splitted into 3 groups varying in the heap size and the number of users (clients):

- Xmx128m running single client
- Xmx128 running 50 clients

### Single thread (user) / -Xmx128m

* messured second step of following script `for run in {1..2}; do  wrk -t1 -c1 -d60s http://127.0.0.1:8080/pingperf/ping/simple; done`
* For Open Liberty `url=http://127.0.0.1:9080/pingperf/ping/simple`
* For spring-boot `url=http://127.0.0.1:8080/simple`
* For quarkus+swarm `url=http://127.0.0.1:8080/ping/simple`

|Microprofile|Start time|Docker mem usage|Req/s|Stdev /s|Latency|Stdev|
|------------|----------|----------------|-----|--------|----------|--------|
|Meecrowave 1.2.7|0.7s|197MB|10.31k|1.79k|118.12us|280.55us|
|Open Liberty 19.02|~3s|250MB|4.99k|629.76|225.59us|372.52us|
|Payara Micro 5.191|6s|320MB|4.96k|1.63k|223.10us|332.64us|
|Wildfly Swarm 2018.3.3|5s|326MB|7.40k|2.43k|186.80us|486.31us|
|Spring-Boot 2.1.1|1.5s|216MB|9.39k|1.29k|144.99us|521.05us|
|Quarkus JVM 0.11|0.68s|144M|18.43k|3.77k|62.11us|269.61us|
|Quarkus Native 0.11|0.023s|430M|17.86k|1.79k|78.28us|362.31us|
|Micronaut 1.0.3|0.907s|200|15.19k|1.84k|84.64us|310.88us|

### 50 threads (users) / -Xmx128m
* messured second step of following script `for run in {1..2}; do  wrk -t4 -c50 -d60s http://127.0.0.1:8080/pingperf/ping/simple; done`
* For Open Liberty `url=http://127.0.0.1:9080/pingperf/ping/simple`
* For spring-boot `url=http://127.0.0.1:8080/simple`
* For quarkus+swarm `url=http://127.0.0.1:8080/ping/simple`

|Microprofile|Start time|Docker mem usage|Req/s|Stdev /s|Latency|Stdev|
|------------|----------|----------------|-----|--------|----------|--------|
|Meecrowave 1.2.7|0.7s|264MB|58879|1.25k*4|1110us|1400us|
|Open Liberty 19.02|~3s|334MB|59205|1.10k*4|990us|1440us|
|Payara Micro 5.191|6s|372MB|40266|0.88k*4|2330msus|4440us|
|Wildfly Swarm 2018.3.3|5s|388MB|62052|1.34k*4|1690us|5550us|
|Spring-Boot 2.1.1|1.5s|291MB|63545|1.58k*4|1200us|1750us|
|Quarkus JVM 0.11|0.57s|140M|80109|2.09k*4|625.98us|453.44us|
|Quarkus Native 0.11|0.023s|450M|28676|0.34k*4|1710us|602.95us|
|Micronaut 1.0.3|0.907s|225M|140216.96|3.68k*4|1030ms|2760us|
