package epam.projects.spring_core.services;

import epam.projects.spring_core.models.Trainer;

public interface TrainerService {
    public void createTrainer(Trainer trainer);
    public void updateTrainer(Trainer trainer);
    public Trainer selectTrainer(Trainer trainer);
}
