package models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "lectures", schema = "school_schema")
public class Lecture {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "lecture_id")
    private Integer ID;
    @Column(name = "name")
    private String name;
    @Column(name = "amount")
    private int amount;
    @Column(name = "description")
    private String description;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Column(name = "lecture_date")
    private LocalDateTime lectureDate;
    @Column(name = "teacher_id")
    private int teacherID;
    @Column(name = "course_id")
    private int courseID;

    public Lecture() {}

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
