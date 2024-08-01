package com.json_parser;

/**
 * ErrorHandler class handles errors that occur during parsing
  */
public class ErrorHandler {
    /**
     * Throws an error with the given message and line number
     * @param message error message
     * @param line line number where the error occurred
      */
    public void throwError(String message, int line) {
        throw new RuntimeException("[Error at line " + line + "] " + message);
    }

    /**
     * Throws an error with the given message
     * @param message error message
      */
    public void throwError(String message) {
        throw new RuntimeException("[Error] " + message);
    }
}
