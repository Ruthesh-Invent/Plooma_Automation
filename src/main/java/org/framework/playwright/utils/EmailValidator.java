package org.framework.playwright.utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import javax.mail.*;
import java.util.Properties;

public class EmailValidator {
    private final String email;
    private final String password;
//    private final ExtentTest logger; // Use ExtentTest for logging

    public EmailValidator(String email, String password) {
        this.email = email;
        this.password = password;
//        this.logger = logger; // Accept ExtentTest from the test class
    }
    public boolean validateEmailReceived(String expectedSubject, int maxRetries, long retryIntervalMs) {
        // IMAP properties for Outlook
        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");
        props.put("mail.imaps.host", "outlook.office365.com");
        props.put("mail.imaps.port", "993");
        props.put("mail.imaps.ssl.enable", "true");
        props.put("mail.debug", "true"); // Enable debug for troubleshooting

        Store store = null;
        Folder inbox = null;
        try {
            // Create session
            Session session = Session.getInstance(props, null);
            store = session.getStore("imaps");
            store.connect("outlook.office365.com", email, password);

            // Open inbox
            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            // Retry logic
            for (int attempt = 1; attempt <= maxRetries; attempt++) {
                // logger.log(Status.INFO, "Checking inbox for email (Attempt " + attempt + "/" + maxRetries + ")");
                Message[] messages = inbox.getMessages(Math.max(1, inbox.getMessageCount() - 9), inbox.getMessageCount());

                for (Message message : messages) {
                    String subject = message.getSubject();
                    if (subject != null && subject.contains(expectedSubject)) {
                        // logger.log(Status.PASS, "Email found with subject: " + subject);
                        return true;
                    }
                }

                // logger.log(Status.INFO, "Email not found, retrying after " + retryIntervalMs + "ms...");
                Thread.sleep(retryIntervalMs);
            }

            // logger.log(Status.FAIL, "Email with subject '" + expectedSubject + "' not found after " + maxRetries + " attempts");
            return false;

        } catch (AuthenticationFailedException e) {
            // logger.log(Status.FAIL, "Authentication failed: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (MessagingException e) {
            // logger.log(Status.FAIL, "Messaging error: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            // logger.log(Status.FAIL, "Retry interrupted: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restore interrupted status
            return false;
        } finally {
            // Close resources safely
            try {
                if (inbox != null && inbox.isOpen()) {
                    inbox.close(false);
                }
                if (store != null && store.isConnected()) {
                    store.close();
                }
            } catch (MessagingException e) {
                // logger.log(Status.WARNING, "Error closing resources: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}