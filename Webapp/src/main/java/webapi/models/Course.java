package webapi.models;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "courses", schema = "school_schema")
public class Course {
    @Id
    @Column(name = "course_id")
    private Integer ID;
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Set<Lecture> lectures = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Set<Teacher> teachers = new HashSet<>();

    public Course() {}

    public Course(Integer ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public Course(Integer ID, String name, Set<Lecture> lectures, Set<Teacher> teachers) {
        this.ID = ID;
        this.name = name;
        this.lectures = lectures;
        this.teachers = teachers;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course that = (Course) o;
        return Objects.equals(ID, that.ID) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name);
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Set<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(Set<Lecture> lectures) {
        this.lectures = lectures;
    }
}
