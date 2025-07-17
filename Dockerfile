FROM eclipse-temurin:17-jre-jammy

ENV TZ="America/Denver"
ARG JAR_NAME=spring-prometheus-1.0-SNAPSHOT.jar
ARG JAR_VERSION
ENV JAR_LOCATION="/app/${JAR_NAME}"

WORKDIR /app

RUN apt-get update \
  && apt-get install -y --no-install-recommends gosu \
  && apt-get -y autoremove \
  && apt-get clean autoclean \
  && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

COPY ./build/libs/${JAR_NAME} "${JAR_LOCATION}"
COPY ./entrypoint.sh /app/entrypoint.sh

RUN set -eux; \
  groupadd --gid 8778 --system app; \
  useradd --uid 8778 --system --shell /bin/bash --create-home --gid app app; \
  mkdir /logs; \
  mkdir -p /data/downloads /data/uploads; \
  chown -R app:app /logs; \
  chown -R app:app /data; \
  chown app:app "${JAR_LOCATION}"; \
  chmod +x /app/entrypoint.sh

VOLUME /logs

ENTRYPOINT ["/app/entrypoint.sh"]
CMD ["java"]