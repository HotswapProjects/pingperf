FROM pingperf/wildfly-hollow-swarm
COPY ./target/pingperf.war ${DEPLOYMENT_DIR}
ENV JAVA_OPTS "$JAVA_OPTS -Djava.net.preferIPv4Stack=true -Xmx128m"
ENV ARCHIVE_NAME pingperf.war
