#!/bin/bash

# Compile
javac -d bin -cp "lib/*;src/main/java" src/main/java/SafeStorage/*.java src/main/java/SafeStorage/Account/*.java

# Run
java --module-path "lib/javafx-sdk-23.0.1/lib" --add-modules javafx.controls,javafx.fxml -cp "bin;lib/*" SafeStorage.SafeStorage