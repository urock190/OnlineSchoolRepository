package webapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users_seq", schema = "school_schema")
public class User {
    @Id
    @Column(unique = true, name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "{name.not-blank}")
    @Size(max = 30, message = "{name.size}")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "{last-name.not-blank}")
    @Size(max = 30, message = "{last-name.size}")
    private String lastName;

    @Column(name = "username", nullable = false)
    @NotBlank
    @Size(min = 1, max = 30)
    private String username;

    @Column(name = "password", length = 60, nullable = false)
    @NotBlank
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    public User() {}

    public User(String firstName, String lastName, String username, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(Integer id, String firstName, String lastName, String username, String password, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((getUsername() == null) ? 0 : getUsername().hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;}
        if (obj == null) {
            return false;}
        if (getClass() != obj.getClass()) {
            return false;}
        final User user = (User) obj;
        return getUsername().equals(user.getUsername());
    }

    @Override
    public String toString() {
        return "User [id = " + id +
                ", firstName = " + firstName +
                ", lastName = " + lastName +
                ", username = " + username +
                ", role = " + role.name() + "]";
    }
}
