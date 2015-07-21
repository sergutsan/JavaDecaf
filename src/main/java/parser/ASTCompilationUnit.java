package main.java.parser;

/* Modified from Transformer example provided in package for JavaCC
 *
 */

/* Copyright (c) 2006, Sun Microsystems, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright notice,
 *       this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Sun Microsystems, Inc. nor the names of its
 *       contributors may be used to endorse or promote products derived from
 *       this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */


/* JJT: 0.2.2 */

import java.io.*;

public class ASTCompilationUnit extends SimpleNode {
  ASTCompilationUnit(int id) {
    super(id);
  }


// Manually inserted code begins here - edited by Sophie Koonin

  public void process (PrintWriter ostr, String className) {
    Token t = begin;
    ASTDecafBlock child;
      /*
       * Children will be null (therefore 0) if the code is straight Java.
       * Otherwise there should be one child for each change to make
       */
      Token encapsulation = new Token(); //this is for the class and main method declarations
      encapsulation.image = "import java.util.Scanner;\n" +  //Assign the class/main method encapsulation code
              "public class " + className + " { \n    " +
              "private Scanner input = new Scanner(System.in);\n    " + //init Scanner for reading from stdin
              "public static void main(String[] args){\n    ";

      for (int i = 0; i < jjtGetNumChildren(); i++) {   //check that there are children
          child = (ASTDecafBlock) jjtGetChild(i);   //the "floating" code will always be first
          child.process(ostr, encapsulation);   //pass Token encapsulation through so it will be printed first
          t = child.end.next;
          if (i==0){    //first iteration (main clause) needs closing brace
              printFinalClosingBrace(ostr, "    "); //closing brace of ain
              encapsulation.image = "\n    private static";  //after first iteration, change encapsulation for methods.
          }
          if (i==(jjtGetNumChildren()-1)){
            printFinalClosingBrace(ostr, "");   //final closing brace of class, no indentation
          }
      }
    while (t != null) {
      print(t, ostr); //Normal code printing
      t = t.next;
    }
  }

    public void printFinalClosingBrace(PrintWriter ostr, String indentation){
        Token close = new Token();
        close.image = "\n" + indentation + "}\n";
        print(close, ostr);
    }
}
