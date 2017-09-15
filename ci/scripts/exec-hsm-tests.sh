#!/bin/bash
#
# All UPERCASE variables are provided externally from this script

set -ex

cd jbehave-minimal-example
  echo "Start building with Maven"
  mvn clean verify

cd ..

echo "Tests done"

echo "Copying Allure results"
cp jbehave-minimal-example/target/allure-results/* test-results/
