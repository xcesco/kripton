#!/bin/bash

mvn clean deploy -Prelease,deploy,kriptonDeploy \
    -Dmaven.test.skip=true