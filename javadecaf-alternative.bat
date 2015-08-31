@echo off
set filename=%1
java -jar JavaDecafCompiler.jar -p %filename%

for /f "delims=." %%a in ("%filename%") do set newfilename=%%a
echo Compiling %newfilename%
"C:\Program Files (x86)\Java\jdk1.8.0_20\bin\javac.exe" %newfilename%.java
echo Compilation finished