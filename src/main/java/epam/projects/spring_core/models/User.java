package epam.projects.spring_core.models;


import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import static java.util.Objects.*;
@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Nonnull
    @NonNull
    private String username;
    @Nonnull
    @NonNull
    @Pattern(regexp = "[A-Z][a-z]+", message = "First letter should be uppercase while all other lowercase")
    private String firstName;
    @Nonnull
    @NonNull
    @Pattern(regexp = "[A-Z][a-z]+", message = "First letter should be uppercase while all other lowercase")
    private String lastName;
    private String password;
    private boolean isActive;

    public User(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = firstName + "." + lastName;
        this.password = password;
        this.isActive = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isActive == user.isActive && userId.equals(user.userId) && username.equals(user.username) && firstName.equals(user.firstName) && lastName.equals(user.lastName) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return hash(userId, username, firstName, lastName, password, isActive);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
