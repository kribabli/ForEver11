package com.example.yoyoiq.ChatFirebase;

public class MessageModel {
    String messageId;
    String senderId;
    String message;

    public MessageModel(String messageId, String senderId, String message) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.message = message;
    }

    public MessageModel() {
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
