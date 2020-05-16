import os
import sys
import xml.etree.ElementTree as ET

# parse pom.xml
root = ET.parse('pom.xml').getroot()
version = root.find('{http://maven.apache.org/POM/4.0.0}version').text
artifactId = root.find('{http://maven.apache.org/POM/4.0.0}artifactId').text
#artifactId = "dev-0.0.1"

# exec docker
dockerId = 'feb18'
dockerBin = '/usr/local/bin/docker'
dockerTag = ':' + version
dockerLabel = dockerId + '/' + artifactId + dockerTag
dockerFile = 'Dockerfile'
dockerBuild = 'build --pull -q --rm'
dockerPush = 'push' 
dockerBuildCommand = dockerBin + ' ' + dockerBuild + ' -t ' + dockerLabel + ' -f ' + dockerFile + ' . '
dockerPushCommand = dockerBin + ' ' + dockerPush + ' ' + dockerLabel

# build docker image
print('execute: ' + dockerBuildCommand)
dockerBuildOutput = ''
dockerBuildOutput = os.popen(dockerBuildCommand).read()
print ("Made docker image " + dockerBuildOutput)

# push to docker hub
print('execute: ' + dockerPushCommand)
dockerPushOutput = ""
dockerPushOutput = os.popen(dockerPushCommand).read()
print ("Pushed docker image " + dockerPushOutput)

