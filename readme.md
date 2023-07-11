``` shell
./gradlew clean assemble dockerPush

docker run -d --hostname alice --pull always -v play:/data/:z -v play-logs:/logs/:Z --name play quangdo/spring-play:1.0-SNAPSHOT

docker logs -f play

docker exec -it play /bin/bash

docker rm -f play && docker volume rm play play-logs

docker image rm $(docker images quangdo/* -q)
```