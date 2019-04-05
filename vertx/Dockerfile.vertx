FROM hotswapagent/hotswap-vm
COPY ./target/vertx-*.jar /work/application.jar
ENV JAVA_OPTS "$JAVA_OPTS -Djava.net.preferIPv4Stack=true -Xmx128m"
ENTRYPOINT java ${JAVA_OPTS} -cp /work/application -jar /work/application.jar
WORKDIR /opt
EXPOSE 8080 8000
