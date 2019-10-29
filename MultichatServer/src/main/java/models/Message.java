package models;

public class Message {
    private Integer id_message;
    private User user;
    private String text;

    public Message(){
    }

    public Message(User user, String text) {
        this.text = text;
        this.user = user;
    }

    public Integer getId_message() {
        return id_message;
    }

    public void setId_message(Integer id_message) {
        this.id_message = id_message;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
