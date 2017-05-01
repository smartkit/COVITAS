package info.smartkit.tas.pojo;

/**
 * Created by smartkit on 28/04/2017.
 * @see: https://www.personalityforge.com/chatbot-api-docs.php
 */
public class Message {
    private String message;
    private int chatBotID;
    private int timestamp;

    public Message(String message, int chatBotID, int timestamp) {
        this.message = message;
        this.chatBotID = chatBotID;
        this.timestamp = timestamp;
    }

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", chatBotID=" + chatBotID +
                ", timestamp=" + timestamp +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getChatBotID() {
        return chatBotID;
    }

    public void setChatBotID(int chatbotID) {
        this.chatBotID = chatbotID;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
