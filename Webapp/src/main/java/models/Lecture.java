package models;

import java.time.LocalDateTime;

public class Lecture {
    private Integer ID;
    private String name;
    private int amount;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime lectureDate;
    private int teacherID;
    private int courseID;

    public Lecture(Integer ID, String name, int amount, String description, LocalDateTime creationDate,
                   LocalDateTime lectureDate, int teacherID, int courseID) {
        this.ID = ID;
        this.name = name;
        this.amount = amount;
        this.description = description;
        this.creationDate = creationDate;
        this.lectureDate = lectureDate;
        this.teacherID = teacherID;
        this.courseID = courseID;
    }

    public Lecture(Integer ID, String name, int amount, String description, LocalDateTime lectureDate,
                   int teacherID, int courseID) {
        this.ID = ID;
        this.name = name;
        this.amount = amount;
        this.description = description;
        this.creationDate = LocalDateTime.now();
        this.lectureDate = lectureDate;
        this.teacherID = teacherID;
        this.courseID = courseID;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLectureDate() {
        return lectureDate;
    }

    public void setLectureDate(LocalDateTime lectureDate) {
        this.lectureDate = lectureDate;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
