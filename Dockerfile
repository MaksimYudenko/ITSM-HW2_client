FROM openjdk:alpine
COPY . /usr/itsm_hw2_client
WORKDIR /usr/itsm_hw2_client
RUN apk update \
&& apk add gradle \
&& gradle fatJar \
&& cd build/libs
ENTRYPOINT ["java","-jar", "/usr/itsm_hw2_client/build/libs/hw2c-1.0-fat.jar"]

#FROM alpine-gradle
#COPY . /usr/itsm_hw2_client
#WORKDIR /usr/itsm_hw2_client
#RUN gradle fatJar \
#&& cd build \
#&& cd libs
#ENTRYPOINT ["java","-jar", "/usr/itsm_hw2_client/build/libs/hw2c-1.0-fat.jar"]