package game.crypt;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Jason Galicia
 */
public class Encrypt {
    
    // Instance variables
    private String text;
    private String salt;
    private MessageDigest digest;
    
    /**
     * Constructor to initialize
     * @param text The text to be hashed
     * @throws NoSuchAlgorithmException 
     */
    public Encrypt(String text) throws NoSuchAlgorithmException {
        this.text = text;
        salt = "n3da942!#lnasf%7gpoo2bdjag";
        digest = MessageDigest.getInstance("SHA-256");
    }
    
    /**
     * Gets the encrypted version of password with the salt
     * @param spot The spot where the salt will be placed
     * @return The encrypted password
     */
    public String getEncryptionWithSalt(int spot) {
        char[] splitText = getTextToArray();
        String newText = "";
        
        for (int i = 0; i < splitText.length; i++) {
            newText += splitText[i];
            if (i == spot) {
                newText += salt;
                for (int j = i+1; j < splitText.length; j++) {
                    newText += splitText[j];
                }
                return hash(newText);
            }
        }
        return hash(newText);
    }
    
    /**
     * Turns the text into a char array
     * @return The char array
     */
    public char[] getTextToArray() {
        return text.toCharArray();
    }
    
    /**
     * Hashes the text to bytes
     * @param wordToHash The word we want to hash
     * @return Returns the text in a byte array
     */
    public String hash(String wordToHash) {
        byte[] hash = digest.digest(wordToHash.getBytes(StandardCharsets.UTF_8));
        return toHexString(hash);
    }
    
    /**
     * Converts bytes to String
     * @param hash The hash in byte array
     * @return The hash in hexadecimal form
     */
    public String toHexString (byte[] hash) {
        // Convert byte array into signum representation  
        BigInteger number = new BigInteger(1, hash);  
  
        // Convert message digest into hex value  
        StringBuilder hexString = new StringBuilder(number.toString(16));  
  
        // Pad with leading zeros 
        while (hexString.length() < 32)  
        {  
            hexString.insert(0, '0');  
        }  
  
        return hexString.toString();
    }
}
