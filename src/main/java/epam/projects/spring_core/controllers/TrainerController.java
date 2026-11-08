package epam.projects.spring_core.controllers;

import epam.projects.spring_core.DTOs.TrainerDto;
import epam.projects.spring_core.models.Trainer;
import epam.projects.spring_core.services.TrainerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/trainer")
public class TrainerController {
    @Autowired
    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping(value = "")
    public Trainer createTrainerProfile(@Valid @RequestBody TrainerDto trainerDto) {
        return trainerService.createTrainer(new Trainer( trainerDto.firstName(), trainerDto.lastName(), trainerDto.specialization()));
    }
    @GetMapping(value = "")
    public Trainer getTrainer(@RequestParam int id) {
        return trainerService.selectTrainer(new Trainer(id));
    }
}
