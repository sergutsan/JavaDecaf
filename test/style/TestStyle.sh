#!/bin/bash

#compile all jdc test files

for file in *.jdc
do
	filejava="${file%.*}"
	filejava="$filejava.java"
	echo "Compiling $file"
	java -cp ../../lib/*;../../target/classes/ parser.JavaDecafCompiler -p $file
	#echo "-----"
	#diff -b -s $filejava ../expected/$filejava
	echo "-------------------"
done
echo "Finished!"
