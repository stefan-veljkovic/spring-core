package epam.projects.spring_core.services.impl;

import epam.projects.spring_core.DAOs.TrainerDao;
import epam.projects.spring_core.models.Trainer;
import epam.projects.spring_core.services.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    private TrainerDao trainerDao;


    @Override
    public void createTrainer(Trainer trainer) {
        this.trainerDao.createTrainer(trainer);
    }

    @Override
    public void updateTrainer(Trainer trainer) {
        this.trainerDao.updateTrainer(trainer);
    }

    @Override
    public Trainer selectTrainer(Trainer trainer) {
        return this.trainerDao.selectTrainer(trainer);
    }
}
