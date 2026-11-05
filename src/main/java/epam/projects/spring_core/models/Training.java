package epam.projects.spring_core.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

import static java.util.Objects.*;

@Setter
@Getter
@Entity
public class Training {
    @Id
    @Embedded
    private TrainingId trainingId;
    private String trainingName;
    @ManyToOne
    private TrainingType trainingType;
    private Date trainingDate;
    private int trainingDuration;
    @ManyToOne
    private Trainer trainer;
    @ManyToOne
    private Trainee trainee;

    public Training(int traineeId, int trainerId, String name, Date date, int duration){
        this.trainingId = new TrainingId(traineeId,trainerId);
        this.trainingName=name;
        this.trainingDate=date;
        this.trainingDuration=duration;
    }

    public Training(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Training training = (Training) o;
        return trainingId.equals(training.trainingId) && trainingName.equals(training.trainingName) && trainingType.equals(training.trainingType) && trainingDate.equals(training.trainingDate) && trainingDuration == training.trainingDuration && Objects.equals(trainer, training.trainer) && trainee.equals(training.trainee);
    }

    @Override
    public int hashCode() {
        return hash(trainingId, trainingName, trainingType, trainingDate, trainingDuration, trainer, trainee);
    }

    @Override
    public String toString() {
        return "Training{" +
                "trainingId=" + trainingId +
                ", trainingName='" + trainingName + '\'' +
                ", trainingType=" + trainingType +
                ", trainingDate=" + trainingDate +
                ", trainingDuration=" + trainingDuration +
                ", trainer=" + trainer +
                ", trainee=" + trainee +
                '}';
    }
}
