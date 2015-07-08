#!/bin/bash

#compile all jdc test files

for file in *.jdc
do
	filejava="${file%.*}"
	filejava="$filejava.java"
	echo "Compiling $file"
	java -cp ../target/classes/ parser.JDCParser $file
	echo "-----"
	diff -b -w -s $filejava expected/$filejava
	echo "-------------------"
done
echo "Finished!"
