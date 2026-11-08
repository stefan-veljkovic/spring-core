package epam.projects.spring_core.services.impl;

import epam.projects.spring_core.DAOs.TraineeDao;
import epam.projects.spring_core.models.Trainee;
import epam.projects.spring_core.services.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraineeServiceImpl implements TraineeService {

    @Autowired
    private TraineeDao traineeDao;

    public Trainee createTrainee(Trainee trainee) {
        return this.traineeDao.createTrainee(trainee);
    }

    public void updateTrainee(Trainee trainee) {
        this.traineeDao.updateTrainee(trainee);
    }

    public void deleteTrainee(Trainee trainee) {
        this.traineeDao.deleteTrainee(trainee);
    }

    public Trainee selectTrainee(Trainee trainee) {
        return this.traineeDao.selectTrainee(trainee);
    }
}
