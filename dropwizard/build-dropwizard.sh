#!/bin/sh
mvn clean package && mvn dockerfile:build
