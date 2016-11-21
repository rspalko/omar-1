#!/bin/bash

# Script to build all OMAR services jar files. This is a workaround for failing
# top-level gradle build. Run from the containing directory with no arguments.

pushd `dirname $0` >/dev/null
export SCRIPT_DIR=`pwd -P`
popd >/dev/null

pushd $SCRIPT_DIR/../../.. >/dev/null
export ROOT_DIR=$PWD
popd >/dev/null

. $SCRIPT_DIR/env.sh

for app in ${O2_APPS[@]} ; do
   echo "BUILDING: $app ..."
   pushd $OMAR_DEV_HOME/apps/$app
   ./gradlew assemble
   if [ $? -ne 0 ];then
       echo "BUILD ERROR: $x failed to build..."
       exit 1
   fi
   popd
done
