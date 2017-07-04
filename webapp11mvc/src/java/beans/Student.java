package beans;

import java.io.Serializable;

public class Student implements Serializable {

    private String ID;
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondLastName;
    private String email;
    private int group;

    public Student() {
    }

    public Student(String ID, String firstName, String middleName, String lastName, String secondLastName, String email, int group) {
        this.ID = ID;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.email = email;
        this.group = group;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student " + ID + ", " + firstName + " " + middleName + " " + lastName + " " + 
                secondLastName + ", email: " + email + ", group: " + group;
    }

     
}
