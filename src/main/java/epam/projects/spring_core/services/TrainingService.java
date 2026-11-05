package epam.projects.spring_core.services;

import epam.projects.spring_core.models.Training;

public interface TrainingService {
    public void createTraining(Training training);
    public Training selectTraining(Training training);
}
