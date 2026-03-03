package org.example.model;

public class SmsNotificationRequest extends NotificationRequest {
    private final String senderId;
    private final String recipentId;
    private final String device;
    private final String message;

    private SmsNotificationRequest(Builder builder) {
        this.senderId = builder.senderId;
        this.recipentId = builder.recipentId;
        this.device = builder.device;
        this.message = builder.message;
    }

    public String getSenderId() { return senderId; }
    public String getRecipentId() { return recipentId; }
    public String getDevice() { return device; }
    public String getMessage() { return message; }

    @Override
    public NotificationType getType() {
        return NotificationType.SMS;
    }

    public static class Builder {
        private String senderId;
        private String recipentId;
        private String device;
        private String message;

        public Builder setSenderId(String senderId) {
            this.senderId = senderId;
            return this;
        }

        public Builder setRecipentId(String recipentId) {
            this.recipentId = recipentId;
            return this;
        }

        public Builder setDevice(String device) {
            this.device = device;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public SmsNotificationRequest build() {
            return new SmsNotificationRequest(this);
        }
    }
}