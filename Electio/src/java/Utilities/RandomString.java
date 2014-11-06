/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 *
 * @author Hardik
 *
 */
public class RandomString {

    public static final String DOMAIN_BASE = "http://localhost:8084/Electio/";
    private static final String CHAR_LIST
            = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890~!@#$%^&-_=+<>?";
    private static Random random;
    public static final String ELECTIO_JAINTELE_EMAIL = "electio@jaintele.com";
    public static final String ELECTIO_JAINTELE_PASSWORD = "electio_2014";

    public static String generateRandomPassword() {
        random = new Random();
        StringBuffer randStr = new StringBuffer();
        int len = 8;
        for (int i = 0; i < len; i++) {
            int number = getRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }

    private static int getRandomNumber() {
        int randomInt = 0;
        randomInt = random.nextInt(CHAR_LIST.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }
    /*
     public static String encryptPassword(String password) {
     MessageDigest msgDigest = null;
     String encrptedPassword = null;

     try {
     msgDigest = MessageDigest.getInstance("SHA-1");
     msgDigest.update(password.getBytes("UTF-8"));
     } catch (NoSuchAlgorithmException ex) {
     //         Logger.getLogger(RandomString.class.getName()).log(Level.SEVERE, null, ex);
     System.out.println("EncryptPassword Error: " + ex.getMessage());
     } catch (UnsupportedEncodingException ex) {
     //  Logger.getLogger(RandomString.class.getName()).log(Level.SEVEREnull, ex);
     System.out.println("EncryptPassword Error: " + ex.getMessage());
     }
     byte rawByte[] = msgDigest.digest();
     encrptedPassword = (new BASE64Encoder()).encode(rawByte);

     return encrptedPassword;
     }
     */

    public String encryptPassword2(String password) {
        String encrypted_password = password;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            encrypted_password = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Err : " + e.getMessage());
        }
        return encrypted_password;
    }
}
