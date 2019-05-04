FROM pingperf/payara-micro
COPY ./target/pingperf.war ${DEPLOYMENT_DIR}
ENV ARCHIVE_NAME pingperf.war
ENV JAVA_OPTS "-Xmx128m"
ENV DEPLOY_OPTS "--nocluster"
