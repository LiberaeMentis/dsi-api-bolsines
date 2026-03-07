#!/bin/sh
# ---------------------------------------------------------------------------
# Maven Wrapper startup script for POSIX systems.
# ---------------------------------------------------------------------------

set -e

BASE_DIR=$(cd "$(dirname "$0")"; pwd -P)
WRAPPER_DIR="$BASE_DIR/.mvn/wrapper"
WRAPPER_JAR="$WRAPPER_DIR/maven-wrapper.jar"
WRAPPER_PROPS="$WRAPPER_DIR/maven-wrapper.properties"

if [ ! -f "$WRAPPER_PROPS" ]; then
  echo "Missing $WRAPPER_PROPS"
  exit 1
fi

if [ ! -f "$WRAPPER_JAR" ]; then
  WRAPPER_URL=$(grep '^wrapperUrl=' "$WRAPPER_PROPS" | cut -d'=' -f2-)
  if command -v curl >/dev/null 2>&1; then
    curl -fsSL "$WRAPPER_URL" -o "$WRAPPER_JAR"
  elif command -v wget >/dev/null 2>&1; then
    wget -q "$WRAPPER_URL" -O "$WRAPPER_JAR"
  else
    echo "Cannot download maven-wrapper.jar (curl/wget not found)."
    exit 1
  fi
fi

exec java -Dmaven.multiModuleProjectDirectory="$BASE_DIR" -classpath "$WRAPPER_JAR" org.apache.maven.wrapper.MavenWrapperMain "$@"
