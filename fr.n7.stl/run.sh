#!/bin/bash

INPUT_FILE=${1:-input.txt}
BASENAME=$(basename "$INPUT_FILE" | cut -d. -f1)
TAM_FILE="${BASENAME}.tam"

echo "⚙ Compilation de $INPUT_FILE vers TAM..."
java -cp "bin/cls:tools/*" fr.n7.stl.minic.Driver "$INPUT_FILE" || { echo "❌ Compilation échouée"; exit 1; }

# Vérification si le fichier a été généré sous le nom input.tam par défaut
[ ! -f "$TAM_FILE" ] && TAM_FILE="input.tam"

echo " Exécution de $TAM_FILE..."
echo "--- Résultat ---"
java -cp "tools/runtam.jar:tools/*" runtam.Run "$TAM_FILE"