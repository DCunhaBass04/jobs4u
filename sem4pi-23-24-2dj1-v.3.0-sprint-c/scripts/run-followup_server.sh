#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies

cd ..
# shellcheck disable=SC2211
export BASE_CP=jobs4u.followup.server/target/jobs4u.followup.server-1.0-SNAPSHOT.jar;jobs4u.followup.server/target/dependency/*;

#REM call the java VM, e.g,
java -cp "$BASE_CP" jobs4u.followup.server.FollowupServerApp
