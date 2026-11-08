package epam.projects.spring_core.services;

import epam.projects.spring_core.models.Trainee;

public interface TraineeService {
    public Trainee createTrainee(Trainee trainee);
    public void updateTrainee(Trainee trainee);
    public void deleteTrainee(Trainee trainee);
    public Trainee selectTrainee(Trainee trainee);
}
