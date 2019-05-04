FROM pingperf/meecrowave
COPY ./target/pingperf.war ${DEPLOYMENT_DIR}
ENV ARCHIVE_NAME pingperf.war
ENV CONTEXT pingperf
ENV APP_OPTS --cdi-conversation=false
ENV JAVA_OPTS="-Dorg.apache.tomcat.util.buf.UDecoder.ALLOW_ENCODED_SLASH=true -Xmx128m -Djava.net.preferIPv4Stack=true"
