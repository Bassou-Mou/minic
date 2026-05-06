#!/bin/bash
echo " Construction du compilateur..."
ant -f minic-build.xml clean generate compile > /dev/null 2>&1 || { echo "❌ Build échoué"; exit 1; }
echo "✅ Build réussi"