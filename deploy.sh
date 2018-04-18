#!/bin/bash

mvn clean package

echo "Uploading files"
scp backend/target/metrics-0.0.1-SNAPSHOT.jar root@cartola.top:/var/metrics/metrics-0.0.1-SNAPSHOT.jar

echo "Reloading service"
ssh root@cartola.top "systemctl daemon-reload"

echo "Restarting application"
ssh root@cartola.top "systemctl restart metrics"

echo "Done"