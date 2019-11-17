package models;

import java.util.Date;

public class Message extends Payload {
    private int idMessage;
    private User user;
    private String text;
    private Date date;

    public Message(){
    }

    public Message(int idMessage, User user){
        this.idMessage = idMessage;
        this.user = user;
    }

    public Message(User user, String text) {
        this.text = text;
        this.user = user;
    }

    public Message(int idMessage, String text) {
        this.idMessage = idMessage;
        this.text = text;
    }

    public Message(int idMessage, User user, String text, Date date) {
        this.idMessage = idMessage;
        this.user = user;
        this.text = text;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Integer getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
