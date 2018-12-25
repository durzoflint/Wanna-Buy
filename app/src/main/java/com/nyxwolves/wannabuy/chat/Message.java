package com.nyxwolves.wannabuy.chat;

public class Message {
    public String time, message, email;

    public Message(String time, String message, String email) {
        this.time = time;
        this.message = message;
        this.email = email;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Message() {
    }
}