FROM openjdk:11
COPY ./src /usr/src/itsm_hw2_client
WORKDIR /usr/src/itsm_hw2_client
RUN javac Client.java
ENTRYPOINT ["java", "Client"]
