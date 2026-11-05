package epam.projects.spring_core.DAOs;

import epam.projects.spring_core.models.Trainer;
import epam.projects.spring_core.storage.AppStorage;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class TrainerDao {

    private static final String NAMESPACE = "trainers";
    private final Map<String, Trainer> storage;

    public TrainerDao(AppStorage appStorage) {
        this.storage = appStorage.getNamespace(NAMESPACE);
    }

    public void createTrainer(Trainer trainer) {
        String trainerUsername = trainer.getFirstName()+"."+trainer.getLastName();
        long trainerMatch = this.storage.values().stream().filter(t->t.getUsername().contains(trainerUsername)).count();
        if(trainerMatch>0)
            trainer.setUsername(trainerUsername+(trainerMatch+1));
        this.storage.put(trainer.getUsername(),trainer);
    }
    public void updateTrainer(Trainer trainer){
        if(this.storage.get(trainer.getUsername())==null)
            throw new IllegalArgumentException("Trainer with the username doesn't exist");
        this.storage.put(trainer.getUsername(),trainer);
    }
    public Trainer selectTrainer(Trainer trainer){
        Optional<Trainer> result = this.storage.values().stream().filter(t->t.getUserId().equals(trainer.getUserId())).findFirst();
        if(result.isEmpty())
            throw new IllegalArgumentException("Trainer with the username doesn't exist");
        return result.get();
    }
}
