package com.csc325Team5.payrollapplication.model;

import java.util.Objects;

public class Note{

    private User sender;
    private User receiver;
    private String note;
    private String time;
    private String receiverID;
    private String senderID;

    public Note(User sender, String note, String time, User receiver) {
        this.sender = sender;
        this.note = note;
        this.time = time;
        this.receiver = receiver;
        this.receiverID = receiver.getID();
        this.senderID = sender.getID();
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note1 = (Note) o;
        return Objects.equals(note, note1.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(note);
    }

    @Override
    public String toString() {
        return "Note{" +
                "senderName='" + sender + '\'' +
                ", receiverName='" + receiver + '\'' +
                ", note='" + note + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
