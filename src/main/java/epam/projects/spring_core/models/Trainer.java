package epam.projects.spring_core.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static java.util.Objects.*;


@Setter
@Getter
@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class Trainer extends User{
    private String specialization;
    @OneToMany
    private List<Training> trainings;
    @ManyToOne
    private TrainingType trainingType;

    public Trainer(String firstName, String lastName, String password) {
        super(firstName, lastName, password);
    }
    public Trainer(int id){
        super(null,null,null);
        super.setUserId(id);
    }
    public Trainer() {
        super(null,null,null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Trainer trainer = (Trainer) o;
        return specialization.equals(trainer.specialization) && trainings.equals(trainer.trainings) && trainingType.equals(trainer.trainingType);
    }

    @Override
    public int hashCode() {
        return hash(super.hashCode(), specialization, trainings, trainingType);
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "specialization='" + specialization + '\'' +
                ", trainings=" + trainings +
                ", trainingType=" + trainingType +
                '}';
    }
}
