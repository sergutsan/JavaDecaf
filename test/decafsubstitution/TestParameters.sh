#!/usr/bin/env bash

printf "Parse only mode"
java -cp ../../target/classes/ parser.JDCCompiler -p TestCodeThenMethod.jdc

printf

printf "Show version"
java -cp ../../target/classes/ parser.JDCCompiler -v TestCodeThenMethod.jdc

printf

printf "Show usage"
java -cp ../../target/classes/ parser.JDCCompiler -help TestCodeThenMethod.jdc


printf

printf "Bad parameter"
java -cp ../../target/classes/ parser.JDCCompiler -foo TestCodeThenMethod.jdc
