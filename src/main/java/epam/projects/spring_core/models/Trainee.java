package epam.projects.spring_core.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

import static java.util.Objects.*;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class Trainee extends User {
    private Date dateOfBirth;
    private String address;
    @OneToMany
    private List<Training> trainings;

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
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
}
