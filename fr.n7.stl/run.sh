#!/bin/bash

# Couleurs pour l'affichage
GREEN='\033[0;32m'
RED='\033[0;31m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${BLUE}=========================${NC}"
echo -e "${BLUE}         MINI-C          ${NC}"
echo -e "${BLUE}=========================${NC}"

# 1. BUILD DU PROJET
echo -e "\n🛠️  Construction du projet (Build)..."

# Au lieu de cacher la sortie dans /dev/null, on l'écrit dans un fichier de log temporaire
BUILD_LOG="build_errors.log"
ant -f minic-build.xml clean generate compile > "$BUILD_LOG" 2>&1
BUILD_STATUS=$?

if [ $BUILD_STATUS -ne 0 ]; then
    echo -e "${RED}❌ Échec de la compilation Java !${NC}"
    echo -e "${YELLOW}--- Détails de l'erreur (extrait) ---${NC}"
    # On affiche les lignes contenant le mot "error" et les quelques lignes autour, ou la fin du fichier
    grep -i -A 5 -B 2 "error\|exception" "$BUILD_LOG" || tail -n 15 "$BUILD_LOG"
    echo -e "${YELLOW}-------------------------------------${NC}"
    echo -e "${RED}👉 Consultez le fichier '$BUILD_LOG' pour voir l'erreur complète.${NC}"
    exit 1
fi

# Si le build réussit, on supprime le fichier de log pour garder le dossier propre
rm -f "$BUILD_LOG"
echo -e "${GREEN}✅ Build réussi !${NC}"

# 2. VÉRIFICATION DU FICHIER D'ENTRÉE
INPUT_FILE=${1:-input.txt}
BASENAME=$(basename "$INPUT_FILE" | cut -d. -f1)
TAM_FILE="${BASENAME}.tam"

if [ ! -f "$INPUT_FILE" ]; then
    echo -e "${RED}❌ Erreur : Le fichier d'entrée '$INPUT_FILE' est introuvable.${NC}"
    echo -e "${YELLOW}Utilisation : ./run.sh [fichier_source.minic]${NC}"
    exit 1
fi

# 3. EXÉCUTION DU COMPILATEUR MINIC
echo -e "\n🚀 Compilation de '$INPUT_FILE' vers '$TAM_FILE'..."
java -cp "bin/cls:tools/*" fr.n7.stl.minic.Driver "$INPUT_FILE"
COMPILER_STATUS=$?

if [ $COMPILER_STATUS -ne 0 ]; then
    echo -e "${RED}❌ Le compilateur MiniC a planté ou a détecté une erreur syntaxique/sémantique.${NC}"
    echo -e "${YELLOW}👉 Regardez l'exception Java ou l'erreur de parsing affichée juste au-dessus.${NC}"
    exit 1
fi

# Ajustement si le fichier .tam généré par défaut s'appelle toujours input.tam
if [ ! -f "$TAM_FILE" ] && [ -f "input.tam" ]; then
    TAM_FILE="input.tam"
fi

# 4. EXÉCUTION DE LA MACHINE VIRTUELLE TAM
echo -e "\n🔍 Vérification du fichier TAM..."
if [ -f "$TAM_FILE" ]; then
    echo -e "${GREEN}✅ Fichier trouvé : $TAM_FILE${NC}"
    echo -e "\n▶️  Exécution dans la TAM VM :"
    echo -e "${BLUE}-------------------------${NC}"
    java -cp "tools/runtam.jar:tools/*" runtam.Run "$TAM_FILE"
    VM_STATUS=$?
    echo -e "\n${BLUE}-------------------------${NC}"

    if [ $VM_STATUS -ne 0 ]; then
        echo -e "${RED}❌ Erreur lors de l'exécution dans la machine virtuelle TAM.${NC}"
        echo -e "${YELLOW}👉 Vérifiez que votre génération de code produit des instructions TAM valides.${NC}"
    else
        echo -e "${GREEN}✅ Exécution terminée sans plantage de la VM.${NC}"
    fi
else
    echo -e "${RED}❌ Fichier TAM ('$TAM_FILE') introuvable !${NC}"
    echo -e "${YELLOW}👉 Le programme s'est exécuté, mais aucun fichier .tam n'a été créé. Vérifiez votre logique de 'CodeGen'.${NC}"
    exit 1
fi