#!/bin/bash

CONTAINER_NAME="my_spring_app"

if [ "$(docker ps -q -f name=$CONTAINER_NAME)" ]; then
    echo "Stopping the container $CONTAINER_NAME..."
    docker stop $CONTAINER_NAME

    if [ $? -ne 0 ]; then
        echo "Failed to stop the container $CONTAINER_NAME."
        exit 1
    fi

    echo "Container $CONTAINER_NAME stopped successfully."
else
    echo "No running container named $CONTAINER_NAME found."
fi

if [ "$(docker ps -aq -f name=$CONTAINER_NAME)" ]; then
    echo "Removing the container $CONTAINER_NAME..."
    docker rm $CONTAINER_NAME

    if [ $? -ne 0 ]; then
        echo "Failed to remove the container $CONTAINER_NAME."
        exit 1
    fi

    echo "Container $CONTAINER_NAME removed successfully."
else
    echo "No container named $CONTAINER_NAME found."
fi
