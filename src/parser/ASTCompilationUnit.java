package parser;

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
    ASTDecafBlock bnode;
      /*
       * Children will be null (therefore 0) if the code is straight Java.
       * Otherwise there should be one child for each change to make
       */
      Token classDec = new Token(); //this is for the class and main method declarations
      classDec.image = "import java.util.Scanner;\n" +  //Assign the class/main method encapsulation code
              "public class " + className + " { \n    " +
              "private Scanner input = new Scanner(System.in);\n    " + //init Scanner for reading from stdin
              "public static void main(String[] args){\n    ";

      if (jjtGetNumChildren() > 0) {    //check that there are children
          bnode = (ASTDecafBlock) jjtGetChild(0);   //the "floating" code will always be first
          bnode.process(ostr, classDec);   //pass classDec through so it will be printed first
          t = bnode.end.next;

          for (int i = 1; i < jjtGetNumChildren(); i++) {   //iterate through remaining children
          }
      }
    while (t != null) {
      print(t, ostr); //Normal code printing
      t = t.next;
    }
      if (jjtGetNumChildren() > 0) {    //Check if there are children - don't do this if the code is plain java
          classDec.image = "}";
          print(classDec, ostr);    //print final closing brace
      }
  }

}
