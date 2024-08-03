package com.json_parser.JSON;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * FileManagement class is responsible for reading and writing files
  */
public class FileManagement {
    /**
     * Function to read a file
     * @param path
     * @return String with the content of the file
     * @throws Exception if the file does not exist or it is invalid
      */
    public static String read(String path) throws Exception {
        if(path == null || path.isEmpty())
            throw new RuntimeException("Invalid file path");
        
        if(!path.endsWith(".json"))
            throw new RuntimeException("Invalid file extension");
        
        // Read the file
        try(BufferedReader br = new BufferedReader(new FileReader(path.toString()))){
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            
            while(line != null){
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error reading file: " + path);
        }
    }
}
