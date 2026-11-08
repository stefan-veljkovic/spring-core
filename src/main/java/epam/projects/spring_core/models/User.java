package epam.projects.spring_core.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Objects;

import static java.util.Objects.*;
@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private Integer userId;
    @NotNull
    private String username;
    @NotNull
    @Pattern(regexp = "[A-Z][a-z]+", message = "First letter should be uppercase while all other lowercase")
    private String firstName;
    @NotNull
    @Pattern(regexp = "[A-Z][a-z]+", message = "First letter should be uppercase while all other lowercase")
    private String lastName;
    private String password;
    private boolean isActive;

    public User(@NotNull String firstName, @NotNull String lastName, @NotNull String password) {
        this.firstName = Objects.requireNonNull(firstName, () -> { throw new IllegalArgumentException("firstName cannot be null"); });
        this.lastName = Objects.requireNonNull(lastName, () -> { throw new IllegalArgumentException("lastName cannot be null"); });
        this.password = Objects.requireNonNull(password, () -> { throw new IllegalArgumentException("password cannot be null"); });
        this.username = firstName + "." + lastName;
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
