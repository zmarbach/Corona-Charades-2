#!/bin/bash

imageName="${1}"
dockerUserName="${2}"
dockerPassword="${3}"

docker build . -t $imageName
docker login -u $dockerUserName -p $dockerPassword
docker tag $imageName $dockerUserName/$imageName
docker image push $dockerUserName/$imageName
docker logout