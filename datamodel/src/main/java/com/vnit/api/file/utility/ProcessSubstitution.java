/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.vnit.api.file.utility;

import java.util.ArrayList;
import java.util.Map;
import java.lang.reflect.Field;

/**
 *
 * @author Pranay Singhal
 */
public class ProcessSubstitution {
        
        ExtractTokens et = new ExtractTokens();

 public String processTemplate(String template) {
        ArrayList<String> hashStrings = et.extractSubstringsByHash(template);

        for (int i = 0; i < hashStrings.size(); i++) {
            String hashString = hashStrings.get(i); 
            String substitutedString = "";
            if(MapsUtil.substitutionMap.containsKey(hashString)) {
                substitutedString = MapsUtil.substitutionMap.get(hashString);
            }
            else {
                substitutedString = processHash(hashString);
                MapsUtil.substitutionMap.put(hashString, substitutedString);
            }

            String var = "^" + hashString + "^";
            template = substitue(template, var, substitutedString);
        }
        
        return template;

    }

    public String processHash(String hashStrings) {
        String substiutedValue = "";
        ArrayList<String> dollarStrings = et.extractTokensByDollar(hashStrings);
        substiutedValue = processTypeOfSubstitution(dollarStrings);
    
        return substiutedValue;
    }

    public String processTypeOfSubstitution(ArrayList<String> dollarStrings) {
        String typeOfSubstitution = dollarStrings.get(0);
        String substituedString = "";
        switch(typeOfSubstitution) {
            case "00": //single substitution
                substituedString = processDollar(dollarStrings);
                break;
        }
        return substituedString;

    }

    public String processDollar(ArrayList<String> dollarStrings) {
        String typeOfSubstitution = dollarStrings.get(1);
        String substiutedValue = "";
        switch (typeOfSubstitution) {
            case "01": // substitution from single map
                String tobeSubstiuted = dollarStrings.get(2);
                ArrayList<String> semiColonStrings = et.extractTokensBySemiColon(tobeSubstiuted);
                substiutedValue = processSemiColon(semiColonStrings);
                break;
            case "02": // substitution from 2 places
                String sub = dollarStrings.get(2);
                ArrayList<String> semicolonstrings = et.extractTokensBySemiColon(sub);
                String condition = processSemiColon(semicolonstrings);
                if(condition.equals("true")) {
                    sub = dollarStrings.get(3);
                    ArrayList<String> constData = et.extractTokensBySemiColon(sub);
                    substiutedValue = processSemiColon(constData);
                }
                
                break;
        }

        return substiutedValue;
    }

    public String processSemiColon(ArrayList<String> semiColonStrings) {
        String literal = semiColonStrings.get(0);
        String substitutedString = "";
        switch(literal) {
            case "m" : // substitute from single map
                String mapToBeUsed = semiColonStrings.get(1);
                String keyOfMap = semiColonStrings.get(2);
                Map<String, ?> map = getMap(mapToBeUsed);
                substitutedString = String.valueOf(map.get(keyOfMap));                    
                break;
            
                case "m2" : // substitute from map of map
                String parentMapString = semiColonStrings.get(1);
                String keyOfParentMap = semiColonStrings.get(2);
                String keyOfChildMap = semiColonStrings.get(3);
                
                Map<String, ?> parentmap = getMap(parentMapString);
                Map<String, String> childMap = (Map<String, String>) parentmap.get(keyOfParentMap);
                substitutedString = childMap.get(keyOfChildMap);
                break;

            case "c" : // substitute a constant
                substitutedString = semiColonStrings.get(1);
                break;
        }

        return substitutedString;
    }

    public String substitue(String source, String variable, String target) {
        StringBuilder sb = new StringBuilder(source);
        int startIndex = sb.indexOf(variable);
        int endIndex = startIndex + variable.length();
        String sub =  sb.replace(startIndex, endIndex, target).toString();

        return sub;
    }

    public Map<String, ?> getMap(String mapName) {
        try {
            Class<?> declaringClass = MapsUtil.class;  
            Field field = declaringClass.getDeclaredField(mapName);

            @SuppressWarnings("unchecked")
            Map<String, ?> map = (Map<String, ?>) field.get(null);
            return map;

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }   
}
