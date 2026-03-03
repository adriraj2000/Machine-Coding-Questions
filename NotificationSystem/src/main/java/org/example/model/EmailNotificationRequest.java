package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class EmailNotificationRequest extends NotificationRequest {
    private final String author;
    private final List<String> recipients;
    private final String subject;
    private final String body;

    private EmailNotificationRequest(Builder builder) {
        this.author = builder.author;
        this.recipients = builder.recipients;
        this.subject = builder.subject;
        this.body = builder.body;
    }

    public String getAuthor() { return author; }
    public List<String> getRecipients() { return recipients; }
    public String getSubject() { return subject; }
    public String getBody() { return body; }

    @Override
    public NotificationType getType() {
        return NotificationType.EMAIL;
    }

    public static class Builder {
        private String author;
        private List<String> recipients = new ArrayList<>();
        private String subject;
        private String body;

        public Builder author(String author) {
            this.author = author;
            return this; // Return this for Method Chaining
        }
        public Builder recipients(List<String> recipients) {
            this.recipients = recipients;
            return this;
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public EmailNotificationRequest build() {
            return new EmailNotificationRequest(this);
        }
    }
}