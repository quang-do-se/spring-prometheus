``` shell
./gradlew clean assemble dockerPush

docker run -d --pull always --name spring-prometheus quangdo/spring-prometheus:1.0-SNAPSHOT

docker logs -f spring-prometheus
xs
docker exec -it spring-prometheus /bin/bash

docker image rm $(docker images quangdo/* -q)
```