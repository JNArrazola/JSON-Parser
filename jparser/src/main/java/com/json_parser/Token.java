package com.json_parser;

/**
 * Token class represents a token in the JSON string
  */
public class Token {
    private final String lexeme; // Value of the token
    private final TokenType type; // Type of the token
    private final int line; // Line number where the token is located

    /**
     * Constructor for Token class
     * @param lexeme lexeme of the token
      */
    public Token(String lexeme, TokenType type, int line) {
        this.lexeme = lexeme;
        this.type = type;
        this.line = line;
    }

    /**
     * Constructor for Token class with no lexeme
     * @param type type of the token
     * @param line line number of the token
      */
    public Token(TokenType type, int line){
        this.lexeme = null;
        this.type = type;
        this.line = line;
    }

    /**
     * Returns the lexeme of the token
     * @return lexeme of the token
      */
    public String getValue() {
        return lexeme;
    }

    /**
     * Returns the type of the token
     * @return type of the token
      */
    public TokenType getType() {
        return type;
    }

    /**
     * Returns the line number of the token
     * @return line number of the token
      */
    public int getLine() {
        return line;
    }
}
