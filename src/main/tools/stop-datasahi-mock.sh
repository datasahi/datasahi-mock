#!/bin/bash

# Ensure script fails on any error
set -e

# Setup workspace directory
if [ -z "$DATASAHI_MOCK_WORKDIR" ]; then
    DATASAHI_MOCK_WORKDIR=$(pwd)
    echo "DATASAHI_MOCK_WORKDIR not set, using current directory: $DATASAHI_MOCK_WORKDIR"
fi

# Define PID file location
PID_FILE="$DATASAHI_MOCK_WORKDIR/datasahi-mock.pid"

# Function to check if process is running
is_process_running() {
    local pid=$1
    if ps -p "$pid" > /dev/null 2>&1; then
        return 0 # Process is running
    else
        return 1 # Process is not running
    fi
}

# Check if PID file exists
if [ ! -f "$PID_FILE" ]; then
    echo "PID file not found at $PID_FILE"
    echo "Datasahi Mock Server does not appear to be running"
    exit 0
fi

# Read PID from file
PID=$(cat "$PID_FILE")

if ! is_process_running "$PID"; then
    echo "Process with PID $PID is not running"
    echo "Removing stale PID file"
    rm "$PID_FILE"
    exit 0
fi

echo "Stopping Datasahi Mock Server (PID: $PID)..."

# First try graceful shutdown with SIGTERM
kill "$PID"

# Wait for up to 30 seconds for the process to stop
WAIT_SECONDS=30
while [ $WAIT_SECONDS -gt 0 ]; do
    if ! is_process_running "$PID"; then
        echo "Datasahi Mock Server stopped successfully"
        rm "$PID_FILE"
        exit 0
    fi
    sleep 1
    WAIT_SECONDS=$((WAIT_SECONDS - 1))
    echo "Waiting for server to stop... ($WAIT_SECONDS seconds remaining)"
done

# If process is still running, force kill it
if is_process_running "$PID"; then
    echo "Server did not stop gracefully. Forcing shutdown..."
    kill -9 "$PID"
    sleep 1

    if is_process_running "$PID"; then
        echo "ERROR: Failed to stop the server"
        exit 1
    else
        echo "Server was forcefully stopped"
        rm "$PID_FILE"
    fi
fi

# Remove PID file if it still exists
[ -f "$PID_FILE" ] && rm "$PID_FILE"

echo "Shutdown completed"