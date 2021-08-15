package com.cosmin.emailblaster.data.model;

public class EmailSender {
    String name;
    String address;
    public EmailSender(String name, String address){
        this.name = name;
        this.address = address;
    }

    public EmailSender(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
