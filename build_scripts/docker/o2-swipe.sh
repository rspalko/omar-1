#!/bin/bash
echo "Stopping o2-swipe container..."
docker stop o2-swipe
echo "Removing o2-swipe container..."
docker rm o2-swipe
echo "Removing o2-swipe image..."
docker rmi ossimlabs/o2-swipe
echo "Running o2-swipe with docker-compose..."
docker-compose run --service-ports --name o2-swipe o2-swipe
