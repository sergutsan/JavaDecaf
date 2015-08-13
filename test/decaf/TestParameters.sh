#!/usr/bin/env bash

printf "Parse only mode\n"
java -cp ../../target/classes/ parser.JavaDecafCompiler -p TestCodeThenMethod.jdc

printf " "

printf "Show version\n"
java -cp ../../target/classes/ parser.JavaDecafCompiler -v TestCodeThenMethod.jdc

printf " "

printf "Show usage\n"
java -cp ../../target/classes/ parser.JavaDecafCompiler -help TestCodeThenMethod.jdc

printf " "

printf "Not enough parameters\n"

java -cp ../../target/classes/ parser.JavaDecafCompiler