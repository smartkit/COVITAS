package info.smartkit.pojo;

/**
 * Created by smartkit on 28/04/2017.
 * @see: https://www.personalityforge.com/chatbot-api-docs.php
 */
public class Message {
    private String message;
    private int chatbotID;
    private int timestamp;

    public Message(String message, int chatbotID, int timestamp) {
        this.message = message;
        this.chatbotID = chatbotID;
        this.timestamp = timestamp;
    }

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", chatbotID=" + chatbotID +
                ", timestamp=" + timestamp +
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

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
