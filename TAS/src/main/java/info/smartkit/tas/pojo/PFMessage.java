package info.smartkit.tas.pojo;

/**
 * Created by smartkit on 28/04/2017.
 * PersonalityForge Message wrapper
 * @see: https://www.personalityforge.com/chatbot-api-php.php
 */
public class PFMessage {

    private Message message;
    private User user;

    public PFMessage() {
    }

    public PFMessage(Message message, User user) {
        this.message = message;
        this.user = user;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PFMessage{" +
                "message=" + message +
                ", user=" + user +
                '}';
    }
}
