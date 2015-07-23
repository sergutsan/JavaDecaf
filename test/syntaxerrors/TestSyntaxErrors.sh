#!/bin/bash

#compile all jdc test files

for file in *.jdc
do
	echo "Exception testing: compiling $file"
	java -cp ../../target/classes/ main.java.parser.JavaDecafCompiler -p $file
	echo "-------------------"
done
echo "Finished!"
