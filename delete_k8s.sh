#!/bin/bash

NAMESPACE="default"

# shellcheck disable=SC2164
cd /home/renos/Downloads/appNetCompany/src/main/java/com/example/app/k8s

delete_resources() {
    RESOURCE_TYPE=$1
    echo "Deleting all $RESOURCE_TYPE in namespace $NAMESPACE..."
    kubectl delete "$RESOURCE_TYPE" --all -n $NAMESPACE

    # shellcheck disable=SC2181
    if [ $? -ne 0 ]; then
        echo "Failed to delete $RESOURCE_TYPE in namespace $NAMESPACE"
    else
        echo "Successfully deleted all $RESOURCE_TYPE in namespace $NAMESPACE"
    fi
}

RESOURCE_TYPES=(
    deployments
    services
    pods
)

for RESOURCE_TYPE in "${RESOURCE_TYPES[@]}"; do
    delete_resources "$RESOURCE_TYPE"
done

echo "All resources in namespace $NAMESPACE have been deleted."
