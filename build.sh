#!/bin/sh

echo
echo "Starting build..."
echo

# Remove old code and old jars
rm -f server/*.class
rm -f client/*.class
rm -f executable/*.jar

# Compile code
javac server/ServerGUI.java
javac client/ClientWindow.java

# Build jar
jar cfm executable/server.jar ./server/manifest.txt server/*.class
jar cfm executable/client.jar ./client/manifest.txt client/*.class

echo
echo "Build Complete."
echo
