package epam.projects.spring_core.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import static java.util.Objects.*;

@Setter
@Getter
@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class Trainee extends User {
    private Date dateOfBirth;
    private String address;
    @OneToMany
    private List<Training> trainings;

    public Trainee(String firstName, String lastName, String password) {
        super(firstName, lastName, password);
    }

    public Trainee(int id){
        this();
        super.setUserId(id);
    }

    public Trainee(){
        super(null,null,null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Trainee trainee = (Trainee) o;
        return dateOfBirth.equals(trainee.dateOfBirth) && address.equals(trainee.address) && trainings.equals(trainee.trainings);
    }

    @Override
    public int hashCode() {
        return hash(super.hashCode(), dateOfBirth, address, trainings);
    }

    @Override
    public String toString() {
        return "Trainee{" +
                "dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", trainings=" + trainings +
                '}';
    }
}
