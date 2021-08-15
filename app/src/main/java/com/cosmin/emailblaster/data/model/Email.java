package com.cosmin.emailblaster.data.model;

public class Email {
    EmailSender sender;
    String message;
    String subject;

    public Email(
            EmailSender sender,
            String message,
            String subject){
        this.sender = sender;
        this.message = message;
        this.subject = subject;
    }

    public Email(){

    }

    public EmailSender getSender() {
        return sender;
    }

    public void setSender(EmailSender sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
