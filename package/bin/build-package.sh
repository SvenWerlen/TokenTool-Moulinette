#!/bin/bash

set -e

PACKAGER=${1}
INSTALLER_TYPE=${2}
MODULE_PATH=${3}
INPUT=${4}
OUTPUT=${5}
JAR=${6}
VERSION=${7}
FILE_ASSOCIATIONS=${8}
APP_ICON=${9}
EXTRA_BUNDLER_ARGUMENTS=${10}

${PACKAGER} \
  create-installer ${INSTALLER_TYPE} \
  --module-path ${MODULE_PATH} \
  --verbose \
  --echo-mode \
  --add-modules java.base,java.management,java.datatransfer,java.desktop,java.scripting,java.xml,jdk.jsobject,jdk.unsupported,jdk.unsupported.desktop,jdk.xml.dom,javafx.controls,javafx.fxml,javafx.swing,javafx.graphics,java.naming,java.sql,jdk.charsets,jdk.crypto.ec \
  --input "${INPUT}" \
  --output "${OUTPUT}" \
  --name Moulinette \
  --main-jar ${JAR} \
  --version ${VERSION} \
  --icon $APP_ICON \
  $EXTRA_BUNDLER_ARGUMENTS \
  --class net.rptools.tokentool.client.TokenTool
