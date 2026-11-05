package epam.projects.spring_core.services.impl;

import epam.projects.spring_core.DAOs.TrainingDao;
import epam.projects.spring_core.models.Training;
import epam.projects.spring_core.services.TrainerService;
import epam.projects.spring_core.services.TrainingService;
import epam.projects.spring_core.storage.AppStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private TrainingDao trainingDao;

    @Autowired
    private AppStorage appStorage;

    public void createTraining(Training training) {
        this.trainingDao.createTraining(training);
    }

    public Training selectTraining(Training training) {
        return this.trainingDao.selectTraining(training);
    }
}
