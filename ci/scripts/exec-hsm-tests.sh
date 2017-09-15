#!/bin/bash
#
# All UPERCASE variables are provided externally from this script

set -ex

cd jbehave-minimal-example
  echo "Start building with Maven"
  mvn clean verify

cd ..

echo "Test" > test-results/

# TODO: Get Allure in here
#cp jbehave-minimal-example/target/allure-results/* test-results/
