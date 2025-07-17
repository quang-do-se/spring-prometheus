``` shell
./gradlew clean assemble

# Build
docker buildx build --platform linux/amd64 -t local/spring-prometheus:1.0-snapshot .

docker compose down && docker compose up -d

# Confirm things are running
docker compose ps
```
Then start the Spring application.