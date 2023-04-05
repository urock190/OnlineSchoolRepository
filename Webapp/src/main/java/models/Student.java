package models;

public class Student {
    private Integer ID;
    private String name;
    private String lastName;
    private String phone;
    private String email;

    public Student(Integer ID, String name, String lastName, String phone, String email) {
        this.ID = ID;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
