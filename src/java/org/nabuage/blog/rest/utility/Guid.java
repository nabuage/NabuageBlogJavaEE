package org.nabuage.blog.rest.utility;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author George
 */
public class Guid {
    
    private static Guid GUID;
    
    private Guid(){
        
    }
    
    public static Guid getInstance(){
        if (null == GUID){
            GUID = new Guid();
        }
        return GUID;
    }
    
    public String create(){
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            byte[] randomByte = new byte[32];
            random.nextBytes(randomByte);
            return Base64.encodeBase64String(randomByte);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Guid.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
    
}
