
docker run \
--detach \
--name=prometheus \
--volume=${PWD}/prometheus/prometheus.yml:/etc/prometheus/prometheus.yml \
--volume=${PWD}/prometheus/alertmanager-rules.yml:/etc/alertmanager/rules.yml \
--publish=9090:9090 \
prom/prometheus:v2.26.0 \
--config.file=/etc/prometheus/promtheus.yml

docker run \
--detach \
--name=alertmanager \
--volume=${PWD}/prometheus/alertmanager-rules.yml:/etc/alertmanager/rules.yml \
--publish=9093:9093 \
prom/alertmanager:v0.21.0