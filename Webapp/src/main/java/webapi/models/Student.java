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
@Table(name = "students", schema = "school_schema")
public class Student {
    @Id
    @Column(name = "student_id", nullable = false)
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
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "student_course_relation",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "course_id"))
    private List<Course> courses = new ArrayList<>();

    public Student() {}

    public Student(Integer ID, String name, String lastName, String phone, String email, List<Course> courses) {
        this.ID = ID;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.courses = courses;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student that = (Student) o;
        return Objects.equals(ID, that.ID) && Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName)
                && Objects.equals(phone, that.phone) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, lastName, phone, email);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
