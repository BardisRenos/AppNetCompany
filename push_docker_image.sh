#!/bin/bash

mvn clean install
mvn compile jib:build
