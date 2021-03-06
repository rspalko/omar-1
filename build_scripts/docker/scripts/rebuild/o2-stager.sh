#!/bin/bash
echo "Stopping o2-stager container..."
docker stop o2-stager
echo "Removing o2-stager container..."
docker rm o2-stager
echo "Removing o2-stager image..."
docker rmi ossimlabs/o2-stager
echo "Running o2-stager with docker-compose..."
docker-compose --file ../../docker-compose-local.yml run -d --service-ports --name o2-stager o2-stager
