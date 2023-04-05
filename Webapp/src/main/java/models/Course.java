package models;

public class Course {
    private Integer ID;
    private String name;

    public Course(Integer ID, String name) {
        this.ID = ID;
        this.name = name;
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
}
