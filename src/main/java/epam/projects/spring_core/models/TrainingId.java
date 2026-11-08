package epam.projects.spring_core.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

import static java.util.Objects.hash;

@Setter
@Getter
@Embeddable
public class TrainingId implements Serializable {
    @Column(name = "id", insertable = false,updatable = false)
    private int id;
    @Column(name = "trainee_id", insertable = false, updatable = false)
    private int traineeId;
    @Column(name = "trainer_id", insertable = false, updatable = false)
    private int trainerId;

    public TrainingId(int id,int traineeId, int trainerId) {
        this.id = id;
        this.traineeId = traineeId;
        this.trainerId = trainerId;
    }

    public TrainingId(){}

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TrainingId that)) return false;
        return id == that.id && traineeId == that.traineeId && trainerId == that.trainerId;
    }

    @Override
    public int hashCode() {
        return hash(id, traineeId, trainerId);
    }

    @Override
    public String toString() {
        return "TrainingId{" +
                "id=" + id +
                ", traineeId=" + traineeId +
                ", trainerId=" + trainerId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(int traineeId) {
        this.traineeId = traineeId;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }
}
