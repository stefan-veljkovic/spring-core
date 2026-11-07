package epam.projects.spring_core.controllers;

import epam.projects.spring_core.DTOs.TraineeDto;
import epam.projects.spring_core.models.Trainee;
import epam.projects.spring_core.services.TraineeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/trainee")
public class TraineeController {

    @Autowired
    private final TraineeService traineeService;

    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @PostMapping(value = "")
    public void createTraineeProfile(@Valid @RequestBody TraineeDto traineeDto) {
        traineeService.createTrainee(new Trainee(traineeDto.firstName(), traineeDto.lastName(), traineeDto.address()));
    }
    @GetMapping(value = "")
    public Trainee getTrainee(@RequestParam int id) {
        return traineeService.selectTrainee(new Trainee(id));
    }
}
