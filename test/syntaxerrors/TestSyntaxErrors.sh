#!/bin/bash

#compile all jdc test files

for file in exceptions/*.jdc
do
	echo "Exception testing: compiling $file"
	java -cp .../target/classes/ parser.JDCParser $file
	echo "-------------------"
done
echo "Finished!"
