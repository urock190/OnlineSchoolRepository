package webapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "teachers", schema = "school_schema")
public class Teacher {
    @Id
    @Column(name = "teacher_id", nullable = false)
    @NotNull(message = "{id.notnull}")
    @Positive(message = "{id.positive}")
    private Integer ID;
    @Column(name = "name", nullable = false)
    @NotBlank(message = "{name.not-blank}")
    @Size(max = 30, message = "{name.size}")
    private String name;
    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "{last-name.not-blank}")
    @Size(max = 30, message = "{last-name.size}")
    private String lastName;
    @Column(name = "phone")
    @Size(max = 25, message = "{phone.size}")
    @Pattern(regexp = "(^\\+(\\(?\\d{3}\\)?-?){4} {0,4}$)", message = "{phone.pattern}")
    private String phone;
    @Column(name = "email", unique = true)
    @Email(message = "{email.valid}")
    @Size(max = 45, message = "{email.size}")
    private String email;
    @Column(name = "course_id")
    @Positive(message = "{id.positive}")
    private Integer courseID;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id")
    private List<Lecture> lectures = new ArrayList<>();

    public Teacher() {}

    public Teacher(Integer ID, String name, String lastName, String phone, String email, int courseID,
                   List<Lecture> lectures) {
        this.ID = ID;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.courseID = courseID;
        this.lectures = lectures;
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

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher that = (Teacher) o;
        return Objects.equals(ID, that.ID) && Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName)
                && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) &&
                Objects.equals(courseID, that.courseID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, lastName, phone, email, courseID);
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }
}
