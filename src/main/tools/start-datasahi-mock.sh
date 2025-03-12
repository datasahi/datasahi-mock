#!/bin/bash

# Ensure script fails on any error
set -e

# Function to check Java version
check_java_version() {
    if ! command -v java >/dev/null 2>&1; then
        echo "Error: Java is not installed or not in PATH"
        exit 1
    }

    # Get Java version
    java_version=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}' | awk -F. '{print $1}')

    if [ "$java_version" -lt 17 ]; then
        echo "Error: Java 17 or higher is required. Current version: $java_version"
        exit 1
    }

    echo "Java version check passed: $java_version"
}

# Setup workspace directory
if [ -z "$DATASAHI_MOCK_WORKDIR" ]; then
    DATASAHI_MOCK_WORKDIR=$(pwd)
    echo "DATASAHI_MOCK_WORKDIR not set, using current directory: $DATASAHI_MOCK_WORKDIR"
fi

# Check if JAR file exists in current directory
if [ ! -f "datasahi.mock-0.1.0.jar" ]; then
    echo "Error: datasahi.mock-0.1.0.jar not found in current directory"
    exit 1
fi

# Create logs directory
LOGS_DIR="$DATASAHI_MOCK_WORKDIR/logs"
mkdir -p "$LOGS_DIR"

# Setup PID file
PID_FILE="$DATASAHI_MOCK_WORKDIR/datasahi-mock.pid"

# Define Java memory options
JAVA_MEMORY_OPTS="-Xms512m -Xmx2048m"

# Define GC logging options
GC_OPTS="-Xlog:gc*:file=$LOGS_DIR/gc-%t.log:time,uptime:filecount=5,filesize=20M"

# Combine all Java options
JAVA_OPTS="$JAVA_MEMORY_OPTS $GC_OPTS"

# Check if process is already running
if [ -f "$PID_FILE" ]; then
    pid=$(cat "$PID_FILE")
    if ps -p "$pid" > /dev/null 2>&1; then
        echo "Error: Datasahi Mock Server is already running with PID $pid"
        exit 1
    else
        # Remove stale PID file
        rm "$PID_FILE"
    fi
fi

# Verify Java version
check_java_version

# Start the server
echo "Starting Datasahi Mock Server..."
nohup java $JAVA_OPTS -jar datasahi.mock-0.1.0.jar > "$LOGS_DIR/datasahi-mock.log" 2>&1 &

# Save PID
echo $! > "$PID_FILE"
echo "Datasahi Mock Server started with PID $!"
echo "Logs are available at: $LOGS_DIR"