package epam.projects.spring_core.services;

import epam.projects.spring_core.models.Trainer;

public interface TrainerService {
    public Trainer createTrainer(Trainer trainer);
    public Trainer updateTrainer(Trainer trainer);
    public Trainer selectTrainer(Trainer trainer);
}
