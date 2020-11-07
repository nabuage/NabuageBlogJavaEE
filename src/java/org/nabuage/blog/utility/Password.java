package org.nabuage.blog.utility;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author George
 */
public class Password {
    private static final String algorithm = "PBKDF2WithHmacSHA1";
    private static final int interationSize = 5000;
    private static final int keySize = 512;
    private static final int saltSize = 48;
    
    public Password() {
        
    }
    
    public String generateSalt() {
        
        try {
            
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            byte[] byteSalt = new byte[saltSize];
            random.nextBytes(byteSalt);
            
            return Base64.encodeBase64String(byteSalt);
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Password.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
        }
        
    }
    
    public String saltPassword(String password, String salt) {
        
        try {
            
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), Base64.decodeBase64(salt), interationSize, keySize);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
            
            return Base64.encodeBase64String(factory.generateSecret(spec).getEncoded());
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Password.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
            
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(Password.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
            
        }
        
    }
    
    public boolean valid(String password, String saltedPassword, String salt) {
        
        return Arrays.equals(Base64.decodeBase64(saltedPassword), Base64.decodeBase64(saltPassword(password, salt)));
        
    }
}
