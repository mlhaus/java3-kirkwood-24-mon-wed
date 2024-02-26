package edu.kirkwood.shared;

import com.azure.communication.email.*;
import com.azure.communication.email.implementation.models.ErrorResponseException;
import com.azure.communication.email.models.*;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.SyncPoller;
import io.github.cdimascio.dotenv.Dotenv;

public class CommunicationService
{
    public static void main(String[] args) {
        sendEmail("marc", "Testing", "Testing again");
    }
    private static EmailClient createEmailClient() {
        Dotenv dotenv = Dotenv.load();
        String connectionString = dotenv.get("EMAIL_CONNECTION");
        EmailClient emailClient = new EmailClientBuilder()
                .connectionString(connectionString)
                .buildClient();
        return emailClient;
    }
    
    public static String sendNewUserEmail(String email, String code) {
        String subject = "LearnX New User";
        String message = "<h2>Welcome to LearnX</h2>";
        message += "<p>Please enter code <b>" + code + "</b> on the website to activate your account</p>";
        boolean sent = CommunicationService.sendEmail(email, subject, message);
        // To do: If the email is not send, delete the user by email and delete the 2fa
        return sent ? code : "";
    }

    public static boolean sendEmail(String toEmailAddress, String subject, String message)    {
        try {
            EmailClient emailClient = createEmailClient();

            EmailAddress toAddress = new EmailAddress(toEmailAddress);

            EmailMessage emailMessage = new EmailMessage()
                    .setSenderAddress(Dotenv.load().get("MAIL_FROM"))
                    .setToRecipients(toAddress)
                    .setSubject(subject)
                    .setBodyHtml(message);

            SyncPoller<EmailSendResult, EmailSendResult> poller = emailClient.beginSend(emailMessage, null);
            PollResponse<EmailSendResult> result = poller.waitForCompletion();
            return true;
        } catch(ErrorResponseException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}

