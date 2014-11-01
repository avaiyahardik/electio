/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author darshit
 */
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    /**
     * Utility method to send simple HTML email
     *
     * @param session
     * @param toEmail
     * @param subject
     * @param body
     */
    public static boolean sendMail(String from, String password, String subject, String message, String to[]) {
        String host = "mail.jaintele.com";
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props, null);

        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(from));
            
            mimeMessage.setHeader("Content-Type", "text/html; charset=UTF-8");
            

            InternetAddress[] toAddresses = new InternetAddress[to.length];

            for (int i = 0; i < to.length; i++) {
                toAddresses[i] = new InternetAddress(to[i]);
            }
            System.out.println("length addresses => " + toAddresses.length);
            mimeMessage.setRecipients(Message.RecipientType.TO, toAddresses);

            mimeMessage.setSubject(subject);
            mimeMessage.setText(message, "utf-8");
            mimeMessage.setContent(message, "text/html; charset=utf-8");

            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();

            return true;

        } catch (MessagingException me) {
            me.printStackTrace();
        }
        return false;
    }

}
