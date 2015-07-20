#!/usr/bin/env bash

printf "Parse only mode\n"
java -cp ../../target/classes/ parser.JDCCompiler -p TestCodeThenMethod.jdc

printf " "

printf "Show version\n"
java -cp ../../target/classes/ parser.JDCCompiler -v TestCodeThenMethod.jdc

printf " "

printf "Show usage\n"
java -cp ../../target/classes/ parser.JDCCompiler -help TestCodeThenMethod.jdc

printf " "

printf "Not enough parameters\n"

java -cp ../../target/classes/ parser.JDCCompiler