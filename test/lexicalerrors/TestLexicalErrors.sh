#!/bin/bash

#compile all jdc test files

for file in *.jdc
do
	echo "Lexical error testing: compiling $file"
	java -cp ../../target/classes/ parser.JDCCompiler $file
	echo "-------------------"
done
echo "Finished!"
