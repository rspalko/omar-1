# The Download Web Service takes multiple
# images or image groups specified in a
# JSON and return a zip archive.
FROM 320588532383.dkr.ecr.us-east-1.amazonaws.com/o2-base
MAINTAINER RadiantBlue Technologies radiantblue.com
LABEL com.radiantblue.version="0.1"\
      com.radiantblue.description="he Download \
      Web Service takes multiple images or image \
      groups specified in a JSON and return a zip \
      archive."\
      com.radiantblue.source=""\
      com.radiantblue.classification="UNCLASSIFIED"
RUN yum -y install o2-download-app
ADD download-app.yml /usr/share/omar/download-app/download-app.yml
ENV DBHOST=${DBHOST}\
    DBPORT=${DBPORT}\
    DBUSER=${DBUSER}\
    DBPASS=${DBPASS}\
    DBNAME=${DBNAME}\
    DOCKER_HOST_IP=${DOCKER_HOST_IP}\
    SPRING_CLOUD_CONFIG_ENABLED=${SPRING_CLOUD_CONFIG_ENABLED}\
    SPRING_CLOUD_DISCOVERY_ENABLED=${SPRING_CLOUD_DISCOVERY_ENABLED}


EXPOSE 8080
CMD ["sh", "/usr/share/omar/download-app/download-app.sh"]
