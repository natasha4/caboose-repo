#!/usr/bin/env bash

cd ..

mvn install -DskipTests=true -Dmaven.javadoc.skip=true -B -V -P aws_eb_deployment