package com.json_parser;

import java.util.ArrayList;

public class Main {
    private static final String JSON_File = "/home/jarrazola/Documents/JSON_Parser/example/example_1.json";
    public static void main(String[] args) {
        ArrayList<Token> tokens;
        try {
            Lexer lexer = new Lexer();
            tokens = lexer.process(JSON_File);
            for(Token token : tokens)
                System.out.println(token.getValue());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}