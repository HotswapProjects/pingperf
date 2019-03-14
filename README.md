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
- Xmx256 running 10 clients
- Xmx256 running 50 clients

### Single thread (user) / -Xmx128m

* messured second step of following script `for run in {1..2}; do  wrk -t1 -c1 -d60s http://127.0.0.1:8080/pingperf/ping/simple; done`
* For Open Liberty `url=http://127.0.0.1:9080/pingperf/ping/simple`
* For spring-boot `url=http://127.0.0.1:8080/simple`
* For quarkus+swarm `url=http://127.0.0.1:8080/ping/simple`

|Microprofile|Start time|Docker mem usage|Req/s|Stdev /s|Latency us|Stdev us|
|------------|----------|----------------|-----|--------|----------|--------|
|Meecrowave 1.2.7|0.7s|197MB|10.31k|1.79k|118.12us|280.55us|
|Open Liberty 19.02|0,7s,real ~3s|250MB|4.93k|739.1|333.86|2.67ms|
|Payara Micro 5.191|6s|320MB|4.96k|1.63k|223.10|332.64us|
|Wildfly Swarm 2018.3.3|5s|326MB|7.40k|2.43k|186.80|486.31us|
|Spring-Boot 2.1.1|1.5s|216MB|9.39k|1.29k|144.99|521.05us|
|Quarkus JVM 0.11|0.57s|151M|11.30k|3.11k|99.70us|233.30us|
|Quarkus Native 0.11|0.003s|16M|8.35k|0.97k|170.69us|542.13us|

### 50 threads (users) / -Xmx256m

### 10 threads (users) / -Xmx256m

