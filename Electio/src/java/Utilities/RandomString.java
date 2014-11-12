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

    //String DOMAIN=request.getRequestURL().substring(0, request.getRequestURL().indexOf("Electio") + 8);
    private static final String CHAR_LIST
            = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890~!@#$%^&-_=+?";
    private static Random random;
    public static final String ELECTIO_JAINTELE_EMAIL = "electio@jaintele.com";
    public static final String ELECTIO_JAINTELE_PASSWORD = "electio_2014";
    public static final String ELECTIO_GMAIL_EMAIL = "sen.daiict@gmail.com";
    public static final String ELECTIO_GMAIL_PASSWORD = "#password2014";

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

    public static String encryptPassword(String password) {
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
            System.out.println("encryptPassword Err : " + e.getMessage());
        }
        return encrypted_password;
    }
}
