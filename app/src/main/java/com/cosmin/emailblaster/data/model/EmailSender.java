package com.cosmin.emailblaster.data.model;

import androidx.annotation.NonNull;

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

    @NonNull
    public String toString(){
        return this.name+"("+this.address+")";
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
