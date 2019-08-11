#!/bin/sh
# Gerar Analisadores lexico e sintatico a partir usando ANTLR
java -cp antlr.jar antlr.Tool -o CompiladorANTLR/src/br/edu/ufabc/compilador gramatica.g

cd CompiladorANTLR/src/br/edu/ufabc/compilador

# Remover arquivos .smap
rm MyParser.smap
rm MyLexer.smap
rm MyParserTokenTypes.txt

# Adicionar nome de pacote nos arquivos
echo "package br.edu.ufabc.compilador;" > temp_file.java
cat MyParser.java >> temp_file.java
mv temp_file.java MyParser.java

echo "package br.edu.ufabc.compilador;" > temp_file.java
cat MyLexer.java >> temp_file.java
mv temp_file.java MyLexer.java

echo "package br.edu.ufabc.compilador;" > temp_file.java
cat MyParserTokenTypes.java >> temp_file.java
mv temp_file.java MyParserTokenTypes.java



