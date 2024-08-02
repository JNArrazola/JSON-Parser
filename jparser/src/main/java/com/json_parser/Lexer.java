package com.json_parser;

import java.util.ArrayList;
import java.util.HashMap;

public class Lexer {
    private int position;
    private ArrayList<Token> tokens;
    private HashMap<String, TokenType> keywords;
    private int line;
    private String content;

    /**
     * Constructor for Lexer class
      */
    private void init(){
        this.position = 0;
        this.line = 1;
        this.tokens = new ArrayList<>();
        this.keywords = new HashMap<>();

        // Keywords
        this.keywords.put("TRUE", TokenType.TRUE);
        this.keywords.put("FALSE", TokenType.FALSE);
        this.keywords.put("NULL", TokenType.NULL);
    }

    public ArrayList<Token> process(String path) throws Exception {
        init();

        content = FileManagement.read(path);

        while(!isAtEnd()){
            switch (content.charAt(position)) {
                case '\n':
                    line++;
                    break;
                case ' ':
                case '\t':
                    break;
                case '"':
                    handleString();
                    break;
                default:
                    break;
            }
            advance();
        }
        tokens.add(new Token(TokenType.EOF, position));
        return tokens;
    }

    private boolean isAtEnd(){
        return position >= content.length();
    }
            
    private void advance(){
        position++;
    }

    private boolean isAlphanumerical(char c){
        return c >= '0' && c <= '9' || c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c == '_' || c == '-' || c == '.';
    }

    private char peek(){
        if(position + 1 >= content.length())
            return '\0';
        return content.charAt(position + 1);
    }

    private char peek(int n){
        if(position + n >= content.length())
            return '\0';
        return content.charAt(position + n);
    }

    // ********* Handlers *********
    private void handleString() throws Exception{
        advance();
        int start = position;

        while (!isAtEnd() && content.charAt(position) != '"'){
            if(content.charAt(position) == '\n')
                ErrorHandler.throwError("Unterminated string: " + content.substring(start, position), line);
            
            advance();
        }
        
        if(isAtEnd())
            ErrorHandler.throwError("Unterminated string: " + content.substring(start, position), line);
        
        tokens.add(new Token(content.substring(start, position), TokenType.STRING, line));
    }
}
