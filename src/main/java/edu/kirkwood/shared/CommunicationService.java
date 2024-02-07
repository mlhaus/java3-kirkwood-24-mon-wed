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

