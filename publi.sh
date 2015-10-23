#!/bin/sh
echo -n "What version is this (e.g. 1.0)? "
read VERSION
mvn clean && mvn package || exit
if [ ! -e ./target/JavaDecaf-${VERSION}.jar ]; then
    echo "Could not find ./target/JavaDecaf-${VERSION}.jar."
    echo "Aborting..."
    exit 1
fi
TARGET_DIR=/tmp/JavaDecaf-$VERSION
if [ -e $TARGET_DIR ]; then
    echo
    echo "ERROR! Directory $TARGET_DIR already exists."
    echo "   This usually indicates that another publication thread is in"
    echo "   operation or that a previous publication was interrupted. "
    echo "   If you are certain that no other publication is in operation,"
    echo "   delete that directory and start again." 
    exit 1
fi
if ! which zip > /dev/null; then
    echo "The program 'zip' could not be found. "
    echo "Please install zip and start again."
    exit 1
fi

echo "Preparing files..."
mkdir $TARGET_DIR
cp ./target/JavaDecaf-${VERSION}.jar $TARGET_DIR
SHELL_SCRIPT=$TARGET_DIR/javadecaf
BATCH_FILE=$TARGET_DIR/javadecaf.bat
HELLO_WORLD=$TARGET_DIR/HelloWorld.jdc
HELLO_WORLD_2=$TARGET_DIR/HiMate.jdc
echo 'echo "#!/bin/bash'                        >  $SHELL_SCRIPT
echo "java -jar JavaDecaf-${VERSION}.jar \$\* " >> $SHELL_SCRIPT
echo ' '                                        >> $SHELL_SCRIPT
echo '@echo off'                                   >  $BATCH_FILE
echo "java -jar %~dp0JavaDecaf-${VERSION}.jar %* " >> $BATCH_FILE
echo ' '                                           >> $BATCH_FILE
echo 'println("Hello world!")'             >  $HELLO_WORLD
echo ' '                                   >> $HELLO_WORLD
echo 'print("Hi! What is your name? ");'        >  $HELLO_WORLD_2
echo 'String s = readLine();'                   >> $HELLO_WORLD_2
echo 'print("What is your age? ");'             >> $HELLO_WORLD_2
echo 'int n = readInt();'                       >> $HELLO_WORLD_2
echo 'println("Nice to meet you, " + s);'       >> $HELLO_WORLD_2
echo ' '                                        >> $HELLO_WORLD_2
cd /tmp
echo "Adding files to ZIP..."
if zip -r JavaDecaf-${VERSION}.zip JavaDecaf-$VERSION/*; then 
   echo File JavaDecaf-${VERSION}.zip created successfully. 
fi

