/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Hardik
 *
 */
public class RandomString {

    private static final String CHAR_LIST
            = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890~!@#$%^&-_=+<>?";
    private static Random random;

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

}
