#!/usr/bin/env bash
echo OFF
echo make sure JAVA_HOME is set to JDK folder
echo make sure maven is on the system PATH
cd ..
mvn $1 package dependency:copy-dependencies surefire-report:report -Daggregate=true checkstyle:checkstyle-aggregate
