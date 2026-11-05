package epam.projects.spring_core.controllers;


import epam.projects.spring_core.models.Trainee;
import epam.projects.spring_core.models.Trainer;
import epam.projects.spring_core.models.Training;
import epam.projects.spring_core.services.TraineeService;
import epam.projects.spring_core.services.TrainerService;
import epam.projects.spring_core.services.TrainingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Component
public class GymFacade {
    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TrainingService trainingService;

    @Autowired
    public GymFacade(TraineeService traineeService, TrainerService trainerService, TrainingService trainingService) {
        this.traineeService = traineeService;
        this.trainerService = trainerService;
        this.trainingService = trainingService;
    }

    public void createTraineeProfile(@Valid String firstName, @Valid String lastName, @Valid String address) {
        traineeService.createTrainee(new Trainee(firstName, lastName, address));
    }

    public Trainee getTrainee(Long id) {
        return traineeService.selectTrainee(new Trainee());
    }

    public void createTrainerProfile(@Valid String firstName, @Valid String lastName, @Valid String specialization) {
        trainerService.createTrainer(new Trainer( firstName, lastName, specialization));
    }

    public Trainer getTrainer(int id) {
        return trainerService.selectTrainer(new Trainer(id));
    }

    public void createTraining(int traineeId, int trainerId, String name, String date, int duration) {
        trainingService.createTraining(new Training(traineeId, trainerId, name, Date.from(LocalDate.parse(date).atStartOfDay(ZoneId.systemDefault()).toInstant()), duration));
    }
}
