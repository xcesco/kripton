#!/bin/bash
cd ./kripton-processor
mvn clean install -Dmaven.test.skip=true
cd ..