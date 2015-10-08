# JavaDecaf
A precompiler for Java Decaf, a simplified version of Java for beginners. 

JavaDecaf requires JRE 1.7 or higher.

The latest version to download is available under "Releases".

If you have downloaded the source code, you can use Maven to create a
JAR archive. Just type 'mvn package' and it will appear on 
directory /target.

## Manual
The manual is included within the ZIP as a PDF file.

Javadoc for this project can be found in the folder /doc.

## Running the Compiler
*Windows:* javadecaf MyFile.jdc

*Mac/Linux:* ./javadecaf MyFile.jdc

## Troubleshooting
#### Windows: NullPointerException in JavaDecafCompiler.compileJava
Rename javadecaf.bat to javadecaf.old, then rename javadecaf-alternative.bat to javadecaf.bat. Run as before.

