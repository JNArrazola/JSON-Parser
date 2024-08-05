package com.json_parser.JSON;

import java.util.ArrayList;

/**
 *  Parser class parses the JSON string
  */
public class Parser {
    private ArrayList<Token> tokens;
    private int position;
    private Element root;

    public Parser() {}

    public Parser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public void parse() throws Exception {
        if(tokens == null)
            ErrorHandler.throwError("No tokens to parse");
    }
    
    public void parse(ArrayList<Token> tokens) throws Exception {
        this.tokens = tokens;
        parse();
    }
    
    // ********** Private Methods **********
    
    private void process(){

    }


    // ********** Auxiliar methods **********
    /**
     * Returns the line number of the token
     * @return line number of the token
      */
    private int getLine(){
        return position;
    }

    /**
     * Checks if the parser is at the end of the tokens
     * @return true if the parser is at the end of the tokens, false otherwise
      */
    private boolean isAtEnd(){
        return position >= tokens.size();
    }

    /**
     * Peeks the next token
     * @return the type of the next token
      */
    private TokenType peek(){
        if(position + 1 >= tokens.size())
            return null;
        return tokens.get(position + 1).getType();
    }

    /**
     * Advances to the next token
     * @return the type of the token
      */
    private TokenType advance(){
        if(!isAtEnd())
            position++;
        return tokens.get(position).getType();
    }

    /**
     * Returns the actual token1
     * @return actual token
      */
    private TokenType getActualToken(){
        return tokens.get(position).getType();
    }

    /**
     * Consume the actual token if it is the expected token
     * @param expected expected token
     * @throws Exception if the actual token is not the expected token
      */
    private void consume(TokenType expected) throws Exception {
        if(getActualToken() != expected)
            ErrorHandler.throwError("Expected " + expected + " but found " + getActualToken(), getLine());
        advance();
    }

}
