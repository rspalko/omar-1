# The stager service currently indexes the data into
# the OMAR system.

FROM 320588532383.dkr.ecr.us-east-1.amazonaws.com/o2-base
MAINTAINER RadiantBlue Technologies radiantblue.com
LABEL com.radiantblue.version="0.1"\
      com.radiantblue.description="The stager service \
      currently indexes the data into the OMAR system."\
      com.radiantblue.source=""\
      com.radiantblue.classification="UNCLASSIFIED"
RUN yum -y install o2-stager-app ossim-kakadu-plugin ossim-geopdf-plugin ossim-png-plugin&&\
  yum -y install ossim-jpeg12-plugin ossim-aws-plugin ossim-sqlite-plugin ossim-gdal-plugin&& \
  yum clean all
ADD stager-app.yml /usr/share/omar/stager-app/stager-app.yml
ENV DBUSER=${DBUSER}\
    DBPASS=${DBPASS}\
    DBHOST=${DBHOST}\
    DBPORT=${DBPORT}\
    DBNAME=${DBNAME}\
    OSSIM_DATA=${OSSIM_DATA}\
    DOCKER_HOST_IP=${DOCKER_HOST_IP}\
    SPRING_CLOUD_CONFIG_ENABLED=${SPRING_CLOUD_CONFIG_ENABLED}\
    SPRING_CLOUD_DISCOVERY_ENABLED=${SPRING_CLOUD_DISCOVERY_ENABLED}


EXPOSE 8080
CMD ["sh", "/usr/share/omar/stager-app/stager-app.sh"]
