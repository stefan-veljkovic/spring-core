package epam.projects.spring_core.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Entity
public class TrainingType {
    @Id
    private String name;
    @OneToMany(mappedBy = "trainingType")
    private List<Trainer> trainers;

    @OneToMany(mappedBy = "trainingType")
    private List<Training> trainings;

    public TrainingType(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingType that = (TrainingType) o;
        return Objects.equals(name, that.name) && Objects.equals(trainers, that.trainers) && Objects.equals(trainings, that.trainings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, trainers, trainings);
    }

    @Override
    public String toString() {
        return "TrainingType{" +
                "name='" + name + '\'' +
                ", trainers=" + trainers +
                ", trainings=" + trainings +
                '}';
    }
}
