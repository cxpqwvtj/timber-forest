FROM java:openjdk-8-jdk-alpine
MAINTAINER cxpqwvtj

RUN apk add --update bash libstdc++ nodejs && rm -rf /var/cache/apk/*

WORKDIR /app

ADD gradle /app/gradle
ADD build.gradle /app/build.gradle
ADD gradlew /app/gradlew
ADD package.json /app/package.json
ADD settings.gradle /app/settings.gradle
ADD src/main/kotlin/timberforest/app/Application.kt /app/src/main/kotlin/timberforest/app/Application.kt
ADD src/test/java/LibraryTest.java /app/src/test/java/LibraryTest.java
ADD .babelrc /app/.babelrc
ADD .eslintrc.js /app/.eslintrc.js
ADD src /app/src
ADD webpack.config.js /app/webpack.config.js
ADD docker/timber-forest/entrypoint.sh /app/entrypoint.sh

RUN npm install && \
    npm run package && \
    ./gradlew build && \
    cp ./build/libs/timber-forest.jar . && \
    rm -rf ./node_modules && \
    rm -rf ./build && \
    rm -rf /root/.gradle && \
    rm -rf /root/.npm

ENTRYPOINT ["/app/entrypoint.sh"]

CMD ["java", "-jar", "timber-forest.jar"]
