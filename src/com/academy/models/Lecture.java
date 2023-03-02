package com.academy.models;

import com.academy.models.lectures.AdditionalMaterial;
import com.academy.models.lectures.Homework;
import com.academy.myDateTimeFormats.DateTimeFormats;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.academy.models.Course.getCounterOfCourses;
import static com.academy.models.Person.getCounterOfTeachers;

public class Lecture extends Models implements Serializable {
    @Serial
    private static final long serialVersionUID = 123446487714L;
    private int amount;
    private String description;
    private static int counterOfLectures;
    private Homework [] homeworks;
    private AdditionalMaterial additionalMaterial;
    private int courseID;
    private int teacherID;
    private LocalDateTime creationDate;
    private LocalDateTime lectureDate;
    @Override
    public String toString() {
        return "Lecture (" +
                "name = '" + getName() + '\'' +
                ", amount = " + amount +
                ", description = \"" + description + '\"'+
                ", ID = " + getID() +
                ", courseID = " + courseID +
                ", teacherID = " + teacherID + ", lecture date = " + DateTimeFormats.lectureDateFormat(lectureDate) + ')';
    }
    public Lecture (String name, int amount, Homework [] homeworks, AdditionalMaterial additionalMaterial, LocalDateTime lectureDate) {
        this.setName(name);
        this.amount = amount;
        this.homeworks = homeworks;
        this.additionalMaterial = additionalMaterial;
        setID(++counterOfLectures);
        courseID = getCounterOfCourses();
        teacherID = getCounterOfTeachers();
        for (Homework homework : homeworks) {
            if (homework == null) continue;
            homework.setLectureID(getID());
            homework.setDeadline(LocalDateTime.of(lectureDate.toLocalDate().plusDays(1), LocalTime.NOON));
        }
        additionalMaterial.setLectureID(getID());
        this.creationDate = LocalDateTime.now();
        this.lectureDate = lectureDate;
    }
    public Lecture(String name, int amount, String description, Homework [] homeworks, AdditionalMaterial additionalMaterial, LocalDateTime lectureDate) {
        this.setName(name);
        this.amount = amount;
        this.description = description;
        this.homeworks = homeworks;
        this.additionalMaterial = additionalMaterial;
        setID(++counterOfLectures);
        courseID = getCounterOfCourses();
        teacherID = getCounterOfTeachers();
        for (Homework homework : homeworks) {
            if (homework == null) continue;
            homework.setLectureID(getID());
            homework.setDeadline(LocalDateTime.of(lectureDate.toLocalDate().plusDays(1), LocalTime.NOON));
        }
        additionalMaterial.setLectureID(getID());
        this.creationDate = LocalDateTime.now();
        this.lectureDate = lectureDate;
    }

    public Lecture(){
        setID(++counterOfLectures);
        courseID = getCounterOfCourses();
        teacherID = getCounterOfTeachers();
        this.creationDate = LocalDateTime.now();
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
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setHomeworks(Homework [] homeworks) {
        this.homeworks = homeworks;
    }

    public void setAdditionalMaterial(AdditionalMaterial additionalMaterial) {
        this.additionalMaterial = additionalMaterial;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
    public int getTeacherID() {
        return teacherID;
    }
    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }
    public int getAmount() {
        return amount;
    }

    public Homework[] getHomeworks() {
        return homeworks;
    }

    public AdditionalMaterial getAdditionalMaterial() {
        return additionalMaterial;
    }

    public static void setCounterOfLectures(int counterOfLectures) {
        Lecture.counterOfLectures = counterOfLectures;
    }

    public int getCourseID() {
        return courseID;
    }
    public static int getCounterOfLectures() {
        return counterOfLectures;
    }
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}