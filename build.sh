#!/bin/bash

SRC_DIR="src"
BIN_DIR="bin"

rm -rf "$BIN_DIR"
mkdir -p "$BIN_DIR"

find "$SRC_DIR" -name "*.java" > sources.txt
javac -d "$BIN_DIR" @sources.txt

if [ $? -eq 0 ]; then
    java -cp "$BIN_DIR" me.rob.bankapp.app.App || echo "Errore durante l'esecuzione."
else
    echo "Errore durante la compilazione."
fi

rm sources.txt