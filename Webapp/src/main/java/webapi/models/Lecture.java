package webapi.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id", referencedColumnName = "lecture_id")
    private Set<AdditionalMaterial> materials = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id", referencedColumnName = "lecture_id")
    private Set<Homework> homeworks = new HashSet<>();

    public Lecture() {}

    public Lecture(Integer ID, String name, int amount, String description, LocalDateTime creationDate,
                   LocalDateTime lectureDate, int teacherID, int courseID, Set<Homework> homeworks,
                   Set<AdditionalMaterial> materials) {
        this.ID = ID;
        this.name = name;
        this.amount = amount;
        this.description = description;
        this.creationDate = creationDate;
        this.lectureDate = lectureDate;
        this.teacherID = teacherID;
        this.courseID = courseID;
        this.homeworks = homeworks;
        this.materials = materials;
    }

    public Lecture(Integer ID, String name, int amount, String description, LocalDateTime lectureDate,
                   int teacherID, int courseID, Set<Homework> homeworks, Set<AdditionalMaterial> materials) {
        this.ID = ID;
        this.name = name;
        this.amount = amount;
        this.description = description;
        this.creationDate = LocalDateTime.now();
        this.lectureDate = lectureDate;
        this.teacherID = teacherID;
        this.courseID = courseID;
        this.homeworks = homeworks;
        this.materials = materials;
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

    public Set<AdditionalMaterial> getMaterials() {
        return materials;
    }

    public void setMaterials(Set<AdditionalMaterial> materials) {
        this.materials = materials;
    }

    public Set<Homework> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(Set<Homework> homeworks) {
        this.homeworks = homeworks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lecture lecture)) return false;
        return getAmount() == lecture.getAmount() && getTeacherID() == lecture.getTeacherID() &&
                getCourseID() == lecture.getCourseID() && Objects.equals(getID(), lecture.getID()) &&
                Objects.equals(getName(), lecture.getName()) && Objects.equals(getDescription(), lecture.getDescription())
                && Objects.equals(getCreationDate(), lecture.getCreationDate()) &&
                Objects.equals(getLectureDate(), lecture.getLectureDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getName(), getAmount(), getDescription(), getCreationDate(), getLectureDate(),
                getTeacherID(), getCourseID());
    }
}
