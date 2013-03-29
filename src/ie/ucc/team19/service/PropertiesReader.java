package ie.ucc.team19.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private String DBServerName;// = "localhost";
    private String DBName;// = "team19";
    private String DBusername;// = "root";
    private String DBpassword;// = "eizeikem";
    private String paypalEmailId;// = "celynch@gmail.com";
    private String mailjetAPIKey;// = "c743932567e67d22f61fd5f06b04893b";
    private String mailjetSecretKey;// = "ef121152d6cbc7f8071dff913a1dc103";
    private String mailjetSenderAddress;// = "team19summer@gmail.com";
    private String mailjetSmtpServer;// = "in.mailjet.com";
    private  String SMTP_PORT;// = "587";

    public PropertiesReader() {
        Properties prop = new Properties();
        try {
            System.out.println("Setup application properties");
            prop.load(new FileInputStream(
                    "C:\\Program Files\\Tomcat\\apache-tomcat-7.0.35\\webapps\\team19\\setup.xml"));
        } catch (IOException e) {
            System.out.println("Error fetching application config");
            e.printStackTrace();
        }

        DBServerName = prop.getProperty("DBServerName");
        DBName = prop.getProperty("DBName");
        DBusername = prop.getProperty("DBusername");
        DBpassword = prop.getProperty("DBpassword");
        paypalEmailId = prop.getProperty("paypalEmailId");
        mailjetAPIKey = prop.getProperty("mailjetAPIKey");
        mailjetSecretKey = prop.getProperty("mailjetSecretKey");
        mailjetSenderAddress = prop.getProperty("mailjetSenderAddress");
        mailjetSmtpServer = prop.getProperty("mailjetSmtpServer");
        SMTP_PORT = prop.getProperty("SMTP_PORT");
    }

    public String getDBServerName() {
        return DBServerName;
    }

    public void setDBServerName(String dBServerName) {
        DBServerName = dBServerName;
    }

    public String getDBName() {
        return DBName;
    }

    public void setDBName(String dBName) {
        DBName = dBName;
    }

    public String getDBusername() {
        return DBusername;
    }

    public void setDBusername(String dBusername) {
        DBusername = dBusername;
    }

    public String getDBpassword() {
        return DBpassword;
    }

    public void setDBpassword(String dBpassword) {
        DBpassword = dBpassword;
    }

    public String getPaypalEmailId() {
        return paypalEmailId;
    }

    public void setPaypalEmailId(String paypalEmailId) {
        this.paypalEmailId = paypalEmailId;
    }

    public String getMailjetAPIKey() {
        return mailjetAPIKey;
    }

    public void setMailjetAPIKey(String mailjetAPIKey) {
        this.mailjetAPIKey = mailjetAPIKey;
    }

    public String getMailjetSecretKey() {
        return mailjetSecretKey;
    }

    public void setMailjetSecretKey(String mailjetSecretKey) {
        this.mailjetSecretKey = mailjetSecretKey;
    }

    public String getMailjetSenderAddress() {
        return mailjetSenderAddress;
    }

    public void setMailjetSenderAddress(String mailjetSenderAddress) {
        this.mailjetSenderAddress = mailjetSenderAddress;
    }

    public String getMailjetSmtpServer() {
        return mailjetSmtpServer;
    }

    public void setMailjetSmtpServer(String mailjetSmtpServer) {
        this.mailjetSmtpServer = mailjetSmtpServer;
    }

    public String getSMTP_PORT() {
        return SMTP_PORT;
    }

    public void setSMTP_PORT(String sMTP_PORT) {
        SMTP_PORT = sMTP_PORT;
    }
}
