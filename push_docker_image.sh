#!/bin/bash

#This scrip creates a new jar file and then pushes the (new) docker image to DockerHub

mvn clean install
mvn compile jib:build
