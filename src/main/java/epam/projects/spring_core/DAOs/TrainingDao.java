package epam.projects.spring_core.DAOs;

import epam.projects.spring_core.models.Trainee;
import epam.projects.spring_core.models.Trainer;
import epam.projects.spring_core.models.Training;
import epam.projects.spring_core.storage.AppStorage;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class TrainingDao {

    private static final String NAMESPACE = "trainings";
    private final Map<String, Training> storage;
    private final Map<String, Trainer> trainerStorage;
    private final Map<String, Trainee> traineeStorage;

    public TrainingDao(AppStorage appStorage) {
        this.storage = appStorage.getNamespace(NAMESPACE);
        this.trainerStorage = appStorage.getNamespace("trainers");
        this.traineeStorage = appStorage.getNamespace("trainees");
    }

    public Training createTraining(Training training){
        if(this.traineeStorage.values().stream().filter(t->t.getUserId().equals(training.getTrainingId().getTraineeId())).findFirst().isEmpty())
            throw new IllegalArgumentException("Training with id "+training.getTrainingId().getTraineeId()+" not found");
        if(this.trainerStorage.values().stream().filter(t->t.getUserId().equals(training.getTrainingId().getTrainerId())).findFirst().isEmpty())
            throw new IllegalArgumentException("Trainer with id "+training.getTrainingId().getTrainerId()+" not found");
        if(this.storage.get(training.getTrainingName())!=null)
            throw new IllegalArgumentException("Training with the name already exist");
        int number = Math.toIntExact(storage.values().stream().filter(storage -> storage.getTrainingId().getTraineeId() == training.getTrainingId().getTraineeId() && storage.getTrainingId().getTrainerId() == training.getTrainingId().getTrainerId()).count());
        training.getTrainingId().setId(number+1);
        this.storage.put(training.getTrainingName(),training);
        return this.storage.get(training.getTrainingName());
    }

    public Training selectTraining(Training training){
        Training training1 = this.storage.get(training.getTrainingName());
        if(training1==null)
            throw new IllegalArgumentException("Training with the name doesn't exist");
        return training1;
    }
}
