FROM java:openjdk-8-jdk
MAINTAINER cxpqwvtj

WORKDIR /app

ADD src /app/src
ADD gradle /app/gradle
ADD .babelrc /app/.babelrc
ADD .eslintrc.js /app/.eslintrc.js
ADD build.gradle /app/build.gradle
ADD gradlew /app/gradlew
ADD package.json /app/package.json
ADD settings.gradle /app/settings.gradle
ADD webpack.config.js /app/webpack.config.js
ADD docker/timber-forest/entrypoint.sh /app/entrypoint.sh

RUN ./gradlew build

CMD /app/entrypoint.sh
