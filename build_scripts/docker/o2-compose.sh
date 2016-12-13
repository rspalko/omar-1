#!/bin/bash

# Builds and runs docker stack for O2.

display_usage() {
  echo
  echo "Usage: ./$(basename $0) [-s] [-h <host>] [-e <env>] <up|down>"
  echo 
  echo "Options:"
  echo "  -s         Run docker-compose with sudo"
  echo "  -h <host>  Set the application domain URL to <host> (default: localhost)"
  echo "  -e <env>   Suffix to reference docker-compose-<env>.yml (default: build)"
  echo
  echo "Arguments:"
  echo "  <up|down>  docker-compose action"
  echo
}

if [ $# < 2 ]; then
  display_usage
  exit 0
fi

pushd `dirname $0` >/dev/null
export SCRIPT_DIR=`pwd -P`
popd >/dev/null

CONFIG_SUFFIX="build"
export DOCKER_HOST_URL="localhost"

while [[ $# > 1 ]]; do
   key="$1"
   case $key in
       -s)
         SUDO_CMD="sudo"
         ;;
       -h)
         DOCKER_HOST_URL="$2"
         shift # past argument
         ;;
       -e)
         CONFIG_SUFFIX="$2"
         shift # past argument
         ;;
       *)
               # unknown option
       ;;
   esac
   shift # past argument or value
done

export APPLICATION_DOMAIN = ${DOCKER_HOST_URL}
DOCKER_ACTION=$1
DOCKER_COMPOSE_FILE="docker-compose-${CONFIG_SUFFIX}.yml"

echo SUDO_CMD = <${SUDO_CMD}>
echo DOCKER_HOST_URL = <${DOCKER_HOST_URL}>
echo APPLICATION_DOMAIN = <${DOCKER_HOST_URL}>
echo CONFIG_SUFFIX = <${CONFIG_SUFFIX}>
echo DOCKER_ACTION = $DOCKER_ACTION
echo DOCKER_COMPOSE_FILE = <$DOCKER_COMPOSE_FILE>

if [ ! -r $DOCKER_COMPOSE_FILE ]; then
  echo "Cannot read the configuration file at <$DOCKER_COMPOSE_FILE>. Aborting."; echo
  display_usage
  exit 1
fi


# Assigns O2_APPS and TAG and functions:
. $SCRIPT_DIR/docker-common.sh

if [ "$2" == "up" ] || [ "$2" == "down" ]; then
  $SUDO_CMD docker-compose -f $DOCKER_COMPOSE_FILE $2 
  if [ $? -ne 0 ]; then
    echo "Ignoring errors..."
  fi
else
  display_usage
  exit 1
fi

exit 0


