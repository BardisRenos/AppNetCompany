#!/bin/bash

IMAGE_NAME="my_spring_app"
CONTAINER_NAME="my_spring_app"
DOCKERFILE_PATH="."
PORT_MAPPING="8089:8088"

echo "Building the Docker image..."
docker build -t $IMAGE_NAME $DOCKERFILE_PATH
# shellcheck disable=SC2181
if [ $? -ne 0 ]; then
    echo "Failed to build the Docker image."
    exit 1
fi

echo "Docker image built successfully."
if [ "$(docker ps -q -f name=$CONTAINER_NAME)" ]; then
    echo "Stopping and removing existing container with the same name..."
    docker stop $CONTAINER_NAME
    docker rm $CONTAINER_NAME
fi

echo "Running the Docker container..."
docker run -d --name $CONTAINER_NAME -p $PORT_MAPPING $IMAGE_NAME
# shellcheck disable=SC2181
if [ $? -ne 0 ]; then
    echo "Failed to run the Docker container."
    exit 1
fi

echo "Docker container is running successfully."
