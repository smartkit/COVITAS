package info.smartkit.tas.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by smartkit on 28/04/2017.
 * @see: https://www.3pillarglobal.com/insights/building-a-microservice-architecture-with-spring-boot-and-docker-part-iii
 */
@Document(collection = "employees")
public class Employee {
    @Id
    private String id;
    private String email;
    private String fullName;
    private String managerEmail;

    public Employee(String id, String email, String fullName, String managerEmail) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.managerEmail = managerEmail;
    }

    public Employee() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", managerEmail='" + managerEmail + '\'' +
                '}';
    }
}

