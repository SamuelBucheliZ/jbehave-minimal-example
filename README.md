# jbehave-minimal-example
Minimal example for jBehave to test setup within Concourse with Allure test output.

## Setup

1. Do `cd ci-test`
2. Run `./setup_keys.sh`
3. Run `./start-concourse.sh`
4. Go to http://localhost:8080
5. Download fly executable
6. Do `install ~/Downloads/fly /usr/local/bin`
7. Run `./setup-pipeline.sh` (username: `concourse`, password: `changeme`)
