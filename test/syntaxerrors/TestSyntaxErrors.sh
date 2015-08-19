#!/bin/bash

#compile all jdc test files

for file in *.jdc
do
	echo "Exception testing: compiling $file"
	java -cp ../../lib/*:../../target/classes/ parser.JavaDecafCompiler -p $file
	echo "-------------------"
done
echo "Finished!"
