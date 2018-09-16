FROM openjdk:alpine
COPY . /usr/itsm_hw2_client
WORKDIR /usr/itsm_hw2_client
RUN apk update \
&& apk add gradle \
&& gradle build \
&& cd build/libs
ENTRYPOINT ["java","-jar", "hw2c-1.0.jar"]
