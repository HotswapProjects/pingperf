FROM pingperf/openliberty-microprofile
ENV DEPLOYMENT_DIR /config/dropins/
COPY ./target/pingperf.war ${DEPLOYMENT_DIR}
COPY ./liberty.jvm.options /config/jvm.options
