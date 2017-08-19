#!/bin/bash

mvn clean package

echo "Uploading files"
scp backend/target/metrics-0.0.1-SNAPSHOT.jar root@138.197.114.203:/var/metrics/metrics-0.0.1-SNAPSHOT.jar

echo "Reloading service"
ssh root@138.197.114.203 "systemctl daemon-reload"

echo "Restarting application"
ssh root@138.197.114.203 "systemctl restart metrics"

echo "Done"