# The Time Lapse Viewer application, nicknamed TLV, is
# an on-demand imagery flipbook.

FROM 320588532383.dkr.ecr.us-east-1.amazonaws.com/o2-base
MAINTAINER RadiantBlue Technologies radiantblue.com
LABEL com.radiantblue.version="0.1"\
      com.radiantblue.description="The Time Lapse \
      Viewer application, nicknamed TLV, is an \
      on-deman imagery flipbook."\
      com.radiantblue.source=""\
      com.radiantblue.classification="UNCLASSIFIED"
RUN yum -y install o2-tlv-app && yum clean all
ADD tlv-app.yml /usr/share/omar/tlv-app/tlv-app.yml
ENV BASE_URL=${BASE_URL}\
    BE_LOOKUP_URL=${BE_LOOKUP_URL}\
    DOCKER_HOST_IP=${DOCKER_HOST_IP}\
    DOCS_URL=${DOCS_URL}\
    GEOCODER_URL=${GEOCODER_URL}\
    WFSURL=${WFSURL}\
    WMSURL=${WMSURL}

EXPOSE 8080
CMD ["sh", "/usr/share/omar/tlv-app/tlv-app.sh"]
