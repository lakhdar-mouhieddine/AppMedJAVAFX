import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GoogleMeetLinkSender {
    private String senderEmail;
    private String senderPassword;

    public void setSenderEmailAndPassword(String email, String password) {
        this.senderEmail = email;
        this.senderPassword = password;
    }

    public void sendGoogleMeetLink(String link, String[] clientEmails, String subject) {
        if (senderEmail == null || senderPassword == null || clientEmails == null || clientEmails.length == 0) {
            throw new IllegalArgumentException("Email credentials and client emails must be set.");
        }

        // Email properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create session with an authenticator
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            for (String clientEmail : clientEmails) {
                // Create a default MimeMessage object
                Message message = new MimeMessage(session);

                // Set From field
                message.setFrom(new InternetAddress(senderEmail));

                // Set To field
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(clientEmail));

                // Set Subject field
                message.setSubject(subject);

                // Set the actual message
                message.setText("Dear client,\n\nPlease join the meeting using the following link: " + link + "\n\nBest regards,\nYour Company");

                // Send the message
                Transport.send(message);

                System.out.println("Google Meet link sent to client: " + clientEmail);
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
