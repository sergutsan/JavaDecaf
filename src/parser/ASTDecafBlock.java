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

/**
 * Original code from JavaCC (see above copyright notice).
 * Modified by Sophie Koonin 2015.
 */

/* JJT: 0.2.2 */

import java.io.*;

public class ASTDecafBlock extends SimpleNode{

  ASTDecafBlock(int id) {
    super(id);
  }

    /**
     * Encapsulate a "loose" block of Decaf code in a Java class with a main method.
     * @param ostr - output stream writer defined in JDCParser
     * @param className - the name of the class (from filename at args[0] in JDCParser)
     */
  public void process (PrintWriter ostr, String className) {
     Token t = begin;  // t is first token in class.
      Token encapsulation = new Token();
      /*
      *This is where the class and main method declarations are defined.
      * Spaces are for indentation to make the output code resemble what good practice Java should look like.
      */
      encapsulation.image = "import java.util.Scanner;\n" +
              "public class " + className + " { \n    " +
              "private Scanner input = new Scanner(System.in);\n\n    " +
              "public static void main(String[] args){\n    ";


      print(encapsulation, ostr);
      String prevToken = ""; // value of previous token image
      while (t != end) {
          /*
           * Replace all JavaDecaf method calls with the Java equivalents.
           * To avoid nesting when used with Java method calls, e.g.
           * System.out.System.out.println, check value of previous token
           * If it's a full stop, it's likely a Java method call.
           */
          if (!prevToken.equals(".")) {
              switch (t.image) {
                  case "println":
                      t.image = "System.out.println";
                      break;
                  case "print":
                      t.image = "System.out.print";
                      break;
              }
          }
          print(t, ostr);
          prevToken = t.image;
          t = t.next;
      }
    // t is final semicolon
    encapsulation.image = "\n    }\n}";
    print(encapsulation, ostr);
  }

}
