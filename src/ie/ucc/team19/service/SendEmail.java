package ie.ucc.team19.service;

import ie.ucc.team19.dao.DBConnectionManager;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Utilises javamail api to create mail message sent via mailjet cloud
 * smtp service. 
 * @author Cormac
 */
public class SendEmail {
    private String APIKey;// = "c743932567e67d22f61fd5f06b04893b";
    private String SecretKey;// = "ef121152d6cbc7f8071dff913a1dc103";
    private String From;// = "team19summer@gmail.com";
    private String smtpServer;// = "in.mailjet.com";
    private String SMTP_PORT;// = "587";
    private DBConnectionManager connector;

    /**
     * Constructor for SendMail. Sets fields read from properties.
     * @param connector
     * @param properties
     */
    public SendEmail(DBConnectionManager connector, PropertiesReader properties) {
        this.connector = connector;
        APIKey = properties.getMailjetAPIKey();
        SecretKey = properties.getMailjetSecretKey();
        From = properties.getMailjetSenderAddress();
        smtpServer = properties.getMailjetSmtpServer();
        SMTP_PORT = properties.getSMTP_PORT();
    }

    /**
     * Create email with given recipient, subject and message, sends via
     * mailjet service.
     * @param toAddress - String for recipient address.
     * @param subject - String for email subject
     * @param mailMessage - String for message content
     */
    public void sendEmail(String toAddress, String subject, String mailMessage) { 
        Properties props = new Properties ();

        props.put("mail.smtp.host", smtpServer);
        props.put("mail.smtp.socketFactory.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SMTP_PORT);

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
            message.setSubject(subject);
            message.setContent(mailMessage, "text/html; charset=utf-8");

            Transport.send (message);
        } catch (MessagingException e) {
            System.out.println("Error sending email");
            throw new RuntimeException (e);
        }
    }

    /**
     * Comments for admins submitted via the contact us page are entered to the db.
     * @param studentId - the id of the student making the comment
     * @param subject - topic comment concerns
     * @param messageText - body of comment
     */
    public void submitComment(String studentId, String subject, String messageText) {
        String query = "INSERT INTO comments VALUES" + "(NULL, ?,?,?,0)";

        Object[] params = {studentId, subject, messageText};
        connector.Insert(query, params);
    }
}
