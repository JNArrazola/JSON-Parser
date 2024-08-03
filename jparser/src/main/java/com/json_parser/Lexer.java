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
        this.keywords.put("true", TokenType.TRUE);
        this.keywords.put("false", TokenType.FALSE);
        this.keywords.put("null", TokenType.NULL);
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
                case ',':
                    tokens.add(new Token(TokenType.COMMA, line));
                    break;
                case ':':
                    tokens.add(new Token(TokenType.COLON, line));
                    break;
                case '[':
                    tokens.add(new Token(TokenType.LBRACKET, line));
                    break;
                case ']':
                    tokens.add(new Token(TokenType.RBRACKET, line));
                    break;
                case '{':
                    tokens.add(new Token(TokenType.LBRACE, line));
                    break;
                case '}':
                    tokens.add(new Token(TokenType.RBRACE, line));
                    break;
                case '(':
                    tokens.add(new Token(TokenType.LPARENTHESIS, line));
                    break;
                case ')':  
                    tokens.add(new Token(TokenType.RPARENTHESIS, line));
                    break;
                default:
                    if(isAlphaNumeric(content.charAt(position)))
                        handleDefault();
                    else
                        ErrorHandler.throwError("Invalid token: " + content.charAt(position), line);
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

    private boolean isAlphaNumeric(char c){
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9';
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

    private void handleDefault(){
        int start = position;

        while(!isAtEnd() && isAlphaNumeric(content.charAt(position))){
            if(content.charAt(position) == '\n')
                ErrorHandler.throwError("Invalid token: " + content.substring(start, position), line);
            
            advance();
        }
        position--;

        String lexeme = content.substring(start, position + 1);

        if(keywords.containsKey(lexeme.toUpperCase()))
            tokens.add(new Token(lexeme.toUpperCase(), keywords.get(lexeme), line));
        else
            try {
                Double.parseDouble(lexeme);
                tokens.add(new Token(lexeme, TokenType.NUMBER, line));
            } catch (NumberFormatException e) {
                ErrorHandler.throwError("Invalid token: " + lexeme, line);
            }
        
    }
}
