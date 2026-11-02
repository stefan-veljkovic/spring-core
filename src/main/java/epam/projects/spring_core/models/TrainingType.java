package epam.projects.spring_core.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class TrainingType {
    @Id
    private String name;
    @OneToMany(mappedBy = "trainingType")
    private List<Trainer> trainers;

    @OneToMany(mappedBy = "trainingType")
    private List<Training> trainings;
}
