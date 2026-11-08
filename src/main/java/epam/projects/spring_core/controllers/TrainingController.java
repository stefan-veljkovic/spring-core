package epam.projects.spring_core.controllers;

import epam.projects.spring_core.models.Training;
import epam.projects.spring_core.services.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@RestController
@RequestMapping(value = "/training")
public class TrainingController {
    @Autowired
    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }


    @PostMapping(value = "")
    public Training createTraining(int traineeId, int trainerId, String name, String date, int duration) {
        return trainingService.createTraining(new Training(0, traineeId, trainerId, name, Date.from(LocalDate.parse(date).atStartOfDay(ZoneId.systemDefault()).toInstant()), duration));
    }
}
