package epam.projects.spring_core.DAOs;

import epam.projects.spring_core.models.Trainee;
import epam.projects.spring_core.storage.AppStorage;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class TraineeDao {

    private static final String NAMESPACE = "trainees";
    private final Map<String, Trainee> storage;

    public TraineeDao(AppStorage storage) {
        this.storage = storage.getNamespace(NAMESPACE);
    }

    public void createTrainee(Trainee trainee){
        String traineeUsername = trainee.getFirstName()+"."+trainee.getLastName();
        long nameMatch = storage.values().stream().filter(t->t.getUsername().contains(traineeUsername)).count();
        if(nameMatch>0){
            trainee.setUsername(traineeUsername+(nameMatch+1));
        }
        this.storage.put(trainee.getUsername(),trainee);
    }
    public void updateTrainee(Trainee trainee){
        if(storage.get(trainee.getUsername())==null)
            throw new IllegalArgumentException("Trainee with the username doesn't exist");
        this.storage.put(trainee.getUsername(),trainee);
    }
    public void deleteTrainee(Trainee trainee){
        if(this.storage.get(trainee.getUsername())==null)
            throw new IllegalArgumentException("Trainee with the username doesn't exist");
        this.storage.remove(trainee.getUsername());
    }
    public Trainee selectTrainee(Trainee trainee){
        Optional<Trainee> trainee1 = this.storage.values().stream().filter(t->t.getUserId().equals(trainee.getUserId())).findFirst();
        if(trainee1.isEmpty())
            throw new IllegalArgumentException("Trainee with the user id doesn't exist");
        return trainee1.get();
    }
}
