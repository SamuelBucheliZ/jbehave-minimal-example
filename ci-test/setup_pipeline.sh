fly -t local login -c http://127.0.0.1:8080
fly -t local set-pipeline -p hsm-test-pipeline -c ../ci/pipelines/hsm-tests/hsm-test-pipeline.yml --load-vars-from ../ci/pipelines/hsm-tests/conf.yml
fly -t local unpause-pipeline -p hsm-test-pipeline
