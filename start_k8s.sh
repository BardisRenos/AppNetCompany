#!/bin/bash

# Define namespace if necessary (leave empty if not using a specific namespace)
NAMESPACE="default"

# shellcheck disable=SC2164
cd /home/renos/Downloads/appNetCompany/src/main/java/com/example/app/k8s

# Apply the deployment
echo "Applying deployment..."
kubectl apply -f accounts-deployment.yml -n $NAMESPACE
if [ $? -ne 0 ]; then
    echo "Failed to apply deployment.yaml"
    exit 1
else
    echo "Successfully applied deployment.yaml"
fi