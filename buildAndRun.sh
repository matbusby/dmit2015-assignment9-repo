#!/bin/sh
if [ $(docker ps -a -f name=dmit2015-assignment9-repo | grep -w dmit2015-assignment9-repo | wc -l) -eq 1 ]; then
  docker rm -f dmit2015-assignment9-repo
fi
mvn clean package && docker build -t org.example/dmit2015-assignment9-repo .
docker run -d -p 9080:9080 -p 9443:9443 --name dmit2015-assignment9-repo org.example/dmit2015-assignment9-repo
