/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nabuage.blog.utility;

/**
 *
 * @author George
 */
public class Text {
    
    private static Text INSTANCE;
    
    public Text() {
        
    }
    
    public static Text getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Text();
        }
        return INSTANCE;
    }
    
    public String incrementLastNumber(String text, String separator) {
        String incrementedText = "";
        String[] textArray = text.split(separator);
        
        if (textArray.length > 0) {
            try {
                int numberString = Integer.parseInt(textArray[textArray.length - 1]);
                incrementedText = text.substring(0, text.lastIndexOf(String.valueOf(numberString))) + String.valueOf(numberString+1);
            }
            catch (NumberFormatException ex) {
                //Ignore
            }
        }
        
        if (incrementedText.equals("")) {
            incrementedText = text + separator + 1;
        }
        
        return incrementedText;
    }
    
}
