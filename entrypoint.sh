#!/usr/bin/env bash

set -ex -o pipefail

# Make sure that Java is run as main process and received sigterm signal properly
if [ "$1" = 'java' ]; then
  exec /usr/sbin/gosu app java ${JAVA_OPTS} -jar "${JAR_LOCATION}"
else
  # Run as user 'app' for any subsequent command
  exec /usr/sbin/gosu app "$@"
fi