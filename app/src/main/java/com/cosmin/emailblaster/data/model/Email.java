package com.cosmin.emailblaster.data.model;

import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;

public class Email {
    EmailSender sender;
    String message;
    String subject;

    String uniqueID;
    String displayTo;

    public Email(
            EmailSender sender,
            String message,
            String subject) {
        this.sender = sender;
        this.message = message;
        this.subject = subject;
    }

    public Email() {

    }

    public Email(EmailMessage fromEmailMessage) throws ServiceLocalException {
        this(
                new EmailSender(
                        fromEmailMessage.getSender().getName(),
                        fromEmailMessage.getSender().getAddress()
                ),
                fromEmailMessage.getBody().toString(),
                fromEmailMessage.getSubject()
        );
        displayTo = fromEmailMessage.getDisplayTo();
        uniqueID = fromEmailMessage.getId().getUniqueId();
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

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getDisplayTo() {
        return displayTo;
    }

    public void setDisplayTo(String displayTo) {
        this.displayTo = displayTo;
    }
}
