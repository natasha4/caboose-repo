#!/usr/bin/env bash

mvn install -DskipTests=true -Dmaven.javadoc.skip=true -B -V -P aws_eb_deployment