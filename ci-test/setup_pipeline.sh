fly -t local login -c http://127.0.0.1:8080
fly -t local set-pipeline -p hsm-test-pipeline -c ../ci/hsm-test-pipeline.yml
fly -t local unpause-pipeline -p hsm-test-pipeline
