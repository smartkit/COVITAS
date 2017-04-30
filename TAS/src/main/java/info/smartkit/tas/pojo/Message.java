package info.smartkit.tas.pojo;

/**
 * Created by smartkit on 28/04/2017.
 * @see: https://www.personalityforge.com/chatbot-api-docs.php
 */
public class Message {
    private String message;
    private int chatbotID;

    public Message(String message, int chatbotID, int timestamp) {
        this.message = message;
        this.chatbotID = chatbotID;
    }

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", chatbotID=" + chatbotID +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getChatbotID() {
        return chatbotID;
    }

    public void setChatbotID(int chatbotID) {
        this.chatbotID = chatbotID;
    }

}
