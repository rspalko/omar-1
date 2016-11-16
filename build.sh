#!/bin/sh

# Script to build all OMAR services jar files. This is a workaround for failing
# top-level gradle build. Run from the containing directory with no arguments.

for x in `ls apps`; do
cd apps/$x
./gradlew assemble
cd ../..
done
