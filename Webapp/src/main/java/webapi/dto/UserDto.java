package webapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import webapi.validation.PasswordMatches;

@PasswordMatches
public class UserDto {
    @NotBlank(message = "{name.not-blank}")
    @Size(max = 30, message = "{name.size}")
    private String firstName;

    @NotBlank(message = "{last-name.not-blank}")
    @Size(max = 30, message = "{last-name.size}")
    private String lastName;

    @NotBlank
    @Size(min = 5, max = 50)
    private String password;

    @NotBlank
    @Size(min = 5, max = 50)
    private String matchingPassword;

    @NotBlank
    @Size(min = 1, max = 30)
    private String username;

    public UserDto() {}

    public UserDto(String firstName, String lastName, String password, String matchingPassword, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    @Override
    public String toString() {
        return "UserDto [firstName = " + firstName +
                ", lastName = " + lastName +
                ", username = " + username;
    }
}
