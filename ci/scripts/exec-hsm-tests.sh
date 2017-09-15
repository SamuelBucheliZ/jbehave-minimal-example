#!/bin/bash

set -x

pushd "${PROJECT_NAME}"

echo "Starting tests with maven"
mvn clean integration-test
test_status=$?
echo "Tests done"

popd

echo "Copying Allure results"
cp ${PROJECT_NAME}/target/allure-results/* test-results/

exit $test_status