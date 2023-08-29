/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.vnit.api.file.utility;

import java.util.ArrayList;

/**
 *
 * @author Pranay Singhal
 */
public class ExtractTokens {
    public ArrayList<String> extractSubstringsByHash(String input) {
        ArrayList<String> substrings = new ArrayList<>();
        int startIndex = input.indexOf('^');
        
        while (startIndex != -1) {
            int endIndex = input.indexOf('^', startIndex + 1);
            
            if (endIndex != -1) {
                String substring = input.substring(startIndex + 1, endIndex);
                if(!substring.contains(" ") && !substring.contains("\t"))
                    substrings.add(substring);
            }
            else {
                break;
            }
  
            startIndex = input.indexOf('^', endIndex + 1);
        }
        
        return substrings;
    }

     public ArrayList<String> extractTokensByDollar(String input) {
        ArrayList<String> tokens = new ArrayList<>();
        
        String[] splitTokens = input.split("\\$");
        
        for (int i = 0; i < splitTokens.length - 1; i++) {
            String token = splitTokens[i];
            if (!token.isEmpty()) {
                tokens.add(token);
            }
        }
        
        String lastToken = splitTokens[splitTokens.length - 1];
        if (!lastToken.isEmpty()) {
            tokens.add(lastToken);
        }
        
        return tokens;
    }

    public ArrayList<String> extractTokensBySemiColon(String input) {
        ArrayList<String> tokens = new ArrayList<>();
        
        String[] splitTokens = input.split("\\:");
        
        for (String token : splitTokens) {
            if (!token.isEmpty()) {
                tokens.add(token);
            }
        }
        
        return tokens;
    }
}
