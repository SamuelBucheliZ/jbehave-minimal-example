#!/bin/bash

set -x

pushd jbehave-minimal-example

echo "Starting tests with maven"
mvn clean integration-test
test_status=$?
echo "Tests done"

popd

echo "Copying Allure results"
cp jbehave-minimal-example/target/allure-results/* test-results/

exit $test_status