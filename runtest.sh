#!/usr/bin/env bash

pushd $(dirname $0)

# install dependencies
mvn clean install -DskipTests

# build and test server
mvn clean verify -pl jsonrpc-server

# launch server
mvn spring-boot:run -pl jsonrpc-server > /dev/null &
_pid=$!
while [[ ! $(curl -sf http://localhost:8080/actuator/health) ]]; do sleep 1; done

# build and test client
mvn clean verify -pl jsonrpc-client

# kill server
kill $_pid

popd