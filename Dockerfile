FROM ubuntu:latest
RUN apt-get update
RUN apt-get update
RUN apt-get install -y curl jq

RUN apt-get install -y p7zip \
    p7zip-full \
    unace \
    zip \
    unzip \
    bzip2

RUN apt-get install -y openjdk-8-jdk 
RUN apt-get install -y openjdk-8-jre
RUN apt-get install dos2unix

#Step 6: Install Maven
# 1- Define Maven version
ARG MAVEN_VERSION=3.6.3
# 2- Define a constant with the working directory
ARG USER_HOME_DIR="/root"

# 3- Define the SHA key to validate the maven download
ARG SHA=c35a1803a6e70a126e80b2b3ae33eed961f83ed74d18fcd16909b2d44d7dada3203f1ffe726c17ef8dcca2dcaa9fca676987befeadc9b9f759967a8cb77181c0

# 4- Define the URL where maven can be downloaded from
ARG BASE_URL=http://apachemirror.wuchna.com/maven/maven-3/${MAVEN_VERSION}/binaries

# 5- Create the directories, download maven, validate the download, install it, remove downloaded file and set links
RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && echo "Downlaoding maven" \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
  \
  && echo "Checking download hash" \
  && echo "${SHA}  /tmp/apache-maven.tar.gz" | sha512sum -c - \
  \
  && echo "Unziping maven" \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  \
  && echo "Cleaning and setting links" \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

# 6- Define environmental variables required by Maven, like Maven_Home directory and where the maven repo is located
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

#workspace
WORKDIR /usr/share/selmvn

# Install git on ubuntu-selenium image
# RUN apt-get install -y git

# Get the repository onto the local system
# RUN git clone https://eaf589b95d9abc6a95cc3e8b8336ea58b42e9bd5:x-oauth-basic@<git>

ADD  target/container-test.jar container-test.jar
ADD  target/libs libs

ADD . .

RUN dos2unix -n healthcheck.sh healthcheck.sh

RUN chmod +x /usr/share/selmvn/healthcheck.sh

# ENTRYPOINT sh healthcheck.sh

