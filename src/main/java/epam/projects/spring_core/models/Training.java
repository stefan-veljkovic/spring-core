package epam.projects.spring_core.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

import static java.util.Objects.*;

@Entity
public class Training {
    @Id
    @Embedded
    private TrainingId trainingId;
    private String trainingName;
    @ManyToOne
    private TrainingType trainingType;
    private Date trainingDate;
    private Date trainingDuration;
    @ManyToOne
    private Trainer trainer;
    @ManyToOne
    private Trainee trainee;

    public TrainingId getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(TrainingId trainingId) {
        this.trainingId = trainingId;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public TrainingType getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(TrainingType trainingType) {
        this.trainingType = trainingType;
    }

    public Date getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
    }

    public Date getTrainingDuration() {
        return trainingDuration;
    }

    public void setTrainingDuration(Date trainingDuration) {
        this.trainingDuration = trainingDuration;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Trainee getTrainee() {
        return trainee;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Training training = (Training) o;
        return trainingId.equals(training.trainingId) && trainingName.equals(training.trainingName) && trainingType.equals(training.trainingType) && trainingDate.equals(training.trainingDate) && trainingDuration.equals(training.trainingDuration) && Objects.equals(trainer, training.trainer) && trainee.equals(training.trainee);
    }

    @Override
    public int hashCode() {
        return hash(trainingId, trainingName, trainingType, trainingDate, trainingDuration, trainer, trainee);
    }
}
