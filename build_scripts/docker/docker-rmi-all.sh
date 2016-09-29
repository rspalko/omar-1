#!/bin/bash
. docker-common.sh

containers=($(docker ps -q))
for container in ${containers[@]} ; do
   echo "Stopping docker container ${container} "
   docker stop ${container}
done

for app in ${O2_APPS[@]} ; do
   getImageName $app $TAG
   exists=$( docker images | grep -c -e "$app[ ]\{2,\}${TAG}") 
   if [ $exists != "0" ]; then
     echo "Removing docker image ${imagename} "
     docker rmi -f ${imagename}
   else
     echo "Skipping missing ${imagename}."
   fi
done

echo; echo Remaining images:
docker images
echo