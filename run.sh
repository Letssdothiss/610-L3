#!/bin/bash

# Create bin directory if it doesn't exist
rm -rf bin
mkdir -p bin

# Extract StringCryption classes to bin directory
cd bin && jar xf ../lib/StringCryption.jar && cd ..

# Convert Windows paths to Unix style
JAVAFX_PATH=$(echo "lib/javafx-sdk-23.0.1/lib" | sed 's/\\/\//g')
BIN_PATH=$(echo "bin" | sed 's/\\/\//g')
SRC_PATH=$(echo "src/main/java" | sed 's/\\/\//g')

# Compile with JavaFX modules and StringCryption
javac --module-path "$JAVAFX_PATH" \
      --add-modules javafx.controls,javafx.fxml \
      -d "$BIN_PATH" \
      -cp "$BIN_PATH" \
      "$SRC_PATH/SafeStorage/"*.java \
      "$SRC_PATH/SafeStorage/controller/"*.java \
      "$SRC_PATH/SafeStorage/model/"*.java \
      "$SRC_PATH/SafeStorage/service/"*.java \
      "$SRC_PATH/SafeStorage/util/"*.java \
      "$SRC_PATH/SafeStorage/view/"*.java

# Run with JavaFX modules and StringCryption
java --module-path "$JAVAFX_PATH" \
     --add-modules javafx.controls,javafx.fxml \
     -cp "$BIN_PATH" \
     SafeStorage.SafeStorage