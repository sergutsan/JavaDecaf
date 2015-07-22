package main.java.ast;

import main.java.parser.Token;

/**
 * Utilities class for AST nodes.
 * @author Sophie Koonin
 * */
public class ASTUtils {

    /**
     * Replace all JavaDecaf method calls with the Java equivalents.
     * To avoid nesting when used with Java method calls, e.g.
     * System.out.System.out.println, check value of previous token
     * If it's a full stop, it's likely a Java method call, so no replacement.
     * @param currentToken the token in question
     * @param prevToken the previous token image
      */

    protected static Token checkForSubstitutions(Token currentToken, String prevToken) {
        if (!prevToken.equals(".")) {
            switch (currentToken.image) {
                case "println":
                    currentToken.image = "System.out.println";
                    break;
                case "print":
                    currentToken.image = "System.out.print";
                    break;
                case "readLine":
                    currentToken.image = "input.readLine"; //input is Scanner
                    break;
                case "readInt":
                    currentToken.image = "input.readInt";
                    break;
            }
        }

        return currentToken;
    }

    /**
     * Fid the indentation level of the current node.
     * @param currentNode the node to count from
     * @return the number of levels to indent
     */
    protected static int getIndentationLevel(Node currentNode) {
        int indentationLevel = 0;
        Node counterNode = currentNode;
        while(!counterNode.jjtGetParent().toString().equals("CompilationUnit")) {
            indentationLevel++;
            counterNode = counterNode.jjtGetParent(); //iterate through parents to find out how indented this should be
        }
        return indentationLevel;
    }

}
