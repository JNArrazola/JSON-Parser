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
        System.out.println(content);

        while(!isAtEnd()){
            switch (getActualChar()) {
                case '1':
                    
                    break;
            
                default:
                    break;
            }
        }

        tokens.add(new Token(TokenType.EOF, position));
        return tokens;
    }

    private boolean isAtEnd(){
        return position >= tokens.size();
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

    private char getActualChar(){
        return content.charAt(position);
    }
}
