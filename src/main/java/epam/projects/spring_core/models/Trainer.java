package epam.projects.spring_core.models;

import jakarta.persistence.*;

import java.util.List;

import static java.util.Objects.*;


@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class Trainer extends User{
    private String specialization;
    @OneToMany
    private List<Training> trainings;
    @ManyToOne
    private TrainingType trainingType;

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public TrainingType getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(TrainingType trainingType) {
        this.trainingType = trainingType;
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
}
