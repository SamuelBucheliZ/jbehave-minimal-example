#!/bin/bash

set -x

pushd "${project_name}"

echo "Starting tests with maven"
mvn clean integration-test
test_status=$?
echo "Tests done"

popd

echo "Copying Allure results"
cp ${project_name}/target/allure-results/* test-results/

exit $test_status