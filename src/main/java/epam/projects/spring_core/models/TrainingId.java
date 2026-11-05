package epam.projects.spring_core.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import static java.util.Objects.hash;

@Setter
@Getter
@Embeddable
public class TrainingId implements Serializable {
    @Column(name = "trainee_id", insertable = false, updatable = false)
    private int traineeId;
    @Column(name = "trainer_id", insertable = false, updatable = false)
    private int trainerId;

    public TrainingId(int traineeId, int trainerId) {
        this.traineeId = traineeId;
        this.trainerId = trainerId;
    }

    public TrainingId(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingId that = (TrainingId) o;
        return traineeId == that.traineeId && trainerId == that.trainerId;
    }

    @Override
    public int hashCode() {
        return hash(traineeId, trainerId);
    }

    @Override
    public String toString() {
        return "TrainingId{" +
                "traineeId=" + traineeId +
                ", trainerId=" + trainerId +
                '}';
    }

}
