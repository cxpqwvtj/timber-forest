FROM centos:7
MAINTAINER cxpqwvtj

RUN yum install -y curl git

ENV TOPBEAT_VERSION="1.2.3"

RUN curl -L -O https://download.elastic.co/beats/topbeat/topbeat-${TOPBEAT_VERSION}-x86_64.rpm && \
    rpm -ivh topbeat-${TOPBEAT_VERSION}-x86_64.rpm && \
    mv /etc/topbeat/topbeat.yml /etc/topbeat/topbeat.yml.org

RUN curl -L -O http://geolite.maxmind.com/download/geoip/database/GeoLiteCity.dat.gz && \
    mkdir -p /usr/share/GeoIP && \
    gunzip -c GeoLiteCity.dat.gz > /usr/share/GeoIP/GeoLiteCity.dat

WORKDIR /topbeat

RUN git clone https://github.com/elastic/beats-dashboards.git

ADD config/ /topbeat/config/
ADD entrypoint.sh /topbeat/entrypoint.sh

CMD /topbeat/entrypoint.sh
