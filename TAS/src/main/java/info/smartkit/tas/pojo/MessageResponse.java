package info.smartkit.tas.pojo;

/**
 * Created by smartkit on 11/05/2017.
 */
public class MessageResponse {
    private String chatBotName;
    private int chatBotID;
    private String message;
    private String emotion;

    public MessageResponse(String chatBotName, int chatBotID, String message, String emotion) {
        this.chatBotName = chatBotName;
        this.chatBotID = chatBotID;
        this.message = message;
        this.emotion = emotion;
    }

    public MessageResponse() {
    }

    public String getChatBotName() {
        return chatBotName;
    }

    public void setChatBotName(String chatBotName) {
        this.chatBotName = chatBotName;
    }

    public int getChatBotID() {
        return chatBotID;
    }

    public void setChatBotID(int chatBotID) {
        this.chatBotID = chatBotID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    @Override
    public String toString() {
        return "MessageResponse{" +
                "chatBotName='" + chatBotName + '\'' +
                ", chatBotID=" + chatBotID +
                ", message='" + message + '\'' +
                ", emotion='" + emotion + '\'' +
                '}';
    }
}
