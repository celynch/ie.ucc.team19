package ie.ucc.team19.service;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class SendEmail {
    private static final String APIKey = "c743932567e67d22f61fd5f06b04893b";
    private static final String SecretKey = "ef121152d6cbc7f8071dff913a1dc103";
    private static final String From = "team19summer@gmail.com";
    private static final String smtpServer = "in.mailjet.com";
    private static final String SMTP_PORT = "587";

    /**
     *
     * @param toAddress
     */
    public void sendEmail(String toAddress, String subject, String mailMessage) { 
        Properties props = new Properties ();
 
        props.put ("mail.smtp.host", smtpServer);
        props.put ("mail.smtp.socketFactory.port", SMTP_PORT);
        props.put ("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put ("mail.smtp.auth", "true");
        props.put ("mail.smtp.port", SMTP_PORT);
 
        Session session = Session.getDefaultInstance (props,
            new javax.mail.Authenticator () {
                protected PasswordAuthentication getPasswordAuthentication () {
                    return new PasswordAuthentication (APIKey, SecretKey);
                }
            });
 
        try {
            Message message = new MimeMessage (session);
            message.setFrom (new InternetAddress (From));
            message.setRecipients (Message.RecipientType.TO, InternetAddress.parse(toAddress));
            message.setSubject (subject);
            message.setText (mailMessage);

            Transport.send (message);
        } catch (MessagingException e) {
            System.out.println("Error sending email");
            throw new RuntimeException (e);
        }
    }
}
