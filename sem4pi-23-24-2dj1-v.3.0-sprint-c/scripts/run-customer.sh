#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies

cd ..
# shellcheck disable=SC2211
export BASE_CP="jobs4u.app.customer.console/target/jobs4u.app.customer.console-1.0-SNAPSHOT.jar:jobs4u.app.customer.console/target/dependency/*"

#REM call the java VM, e.g,
java -cp "$BASE_CP" jobs4u.app.customer.console.CustomerApp
