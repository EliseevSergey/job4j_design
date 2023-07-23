package ru.job4j.ood.baddip;

public class User {
    public void sendEmail(String text, String email) {
        GmailSender gmailSender = new GmailSender();
        gmailSender.send(text, email);
    }

    private class GmailSender {
        public void send(String text, String email) {
            System.out.printf("Send %s to email ", text, email);
        }
    }
}
