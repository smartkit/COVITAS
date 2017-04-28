package info.smartkit.pojo;

/**
 * Created by smartkit on 28/04/2017.
 * @see: https://www.personalityforge.com/chatbot-api-docs.php
 */
public class User {
    private String externalID;
    private String firstName;
    private String lastName;
    private String gender;//m,f;

    public User(String externalID, String firstName, String lastName, String gender) {
        this.externalID = externalID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public User() {
    }

    public String getExternalID() {
        return externalID;
    }

    public void setExternalID(String externalID) {
        this.externalID = externalID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "externalID='" + externalID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
