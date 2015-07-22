#!/usr/bin/env bash

#compile jjt
jjtree JavaDecaf.jjt
#move the output jj file to the current folder
mv ../main/java/ast/JavaDecaf.jj JavaDecaf.jj
#compile jj
javacc JavaDecaf.jj