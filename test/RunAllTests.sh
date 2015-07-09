#!/usr/bin/env bash

cd javacode
printf "Testing handling of normal Java code\n"
bash TestJavaCode.sh

cd ../decafsubstitution
printf "Testing method substitution and encapsulation\n"

bash TestDecafSubstitution.sh

cd ../syntaxerrors
printf "Testing handling of syntax errors\n"
bash TestSyntaxErrors.sh