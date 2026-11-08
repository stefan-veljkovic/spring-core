package epam.projects.spring_core.DAOs;

import epam.projects.spring_core.models.Trainer;
import epam.projects.spring_core.storage.AppStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TrainerDaoTests {

    @MockBean
    public AppStorage appStorage;

    public TrainerDao trainerDao;

    @BeforeAll
    void setup() {
        Map<String, Object> trainersMap = new HashMap<>();
        Trainer trainerOne = new Trainer("Trainerone","Trainerone","trainerone");
        trainerOne.setUserId(1);
        trainersMap.put("Trainerone.Trainerone", trainerOne);
        Trainer trainerTwo = new Trainer("Trainertwo","Trainertwo","trainertwo");
        trainerTwo.setUserId(2);
        trainersMap.put("Trainertwo.Trainertwo", trainerTwo);
        Trainer trainerThree = new Trainer("Trainertthree","Trainertthree","trainertthree");
        trainerThree.setUserId(3);
        trainersMap.put("Trainerthree.Trainerthree", trainerThree);
        Trainer trainerFour = new Trainer("Trainerfour","Trainerfour","trainerfour");
        trainerFour.setUserId(4);
        trainersMap.put("Trainergfour.Trainerfour", trainerFour);
        Trainer trainerFive = new Trainer("Trainerfive","Trainerfive","trainerfive");
        trainerFive.setUserId(5);
        trainersMap.put("Trainerfive.Trainerfive",trainerFive);
        Mockito.when(this.appStorage.getNamespace("trainers")).thenReturn(trainersMap);
        trainerDao = new TrainerDao(appStorage);
    }

    @Test
    void createTrainerReturnsTrainer(){
        Trainer trainer = new Trainer("Trainersix","Trainersix","trainersix");
        Trainer result = this.trainerDao.createTrainer(trainer);
        Assertions.assertNotNull(result.getUserId());
    }

    @Test
    void createTrainerThrowsIllegalArgumentException(){
        Assertions.assertAll(
                ()->Assertions.assertThrows(IllegalArgumentException.class, ()->this.trainerDao.createTrainer(new Trainer(null,"Trainersix","trainersix"))),
                ()->Assertions.assertThrows(IllegalArgumentException.class, ()->this.trainerDao.createTrainer(new Trainer("Trainersix",null,"trainersix"))),
                ()->Assertions.assertThrows(IllegalArgumentException.class, ()->this.trainerDao.createTrainer(new Trainer("Trainersix","Trainersix",null)))
        );
    }

    @Test
    void updateTrainerReturnsTrainer(){
        Trainer trainer = new Trainer("Trainerone","Trainerone","trainerone");
        trainer.setSpecialization("Specializationone");
        trainer.setUserId(1);
        Trainer result = this.trainerDao.updateTrainer(trainer);
        Assertions.assertEquals(trainer.getSpecialization(),result.getSpecialization());
    }

    @Test
    void updateTrainerThrowsIllegalArgumentException(){
        Assertions.assertThrows(IllegalArgumentException.class,()->trainerDao.updateTrainer(new Trainer("Trainersix","Trainersix","trainersix")));
    }

    @Test
    void selectTrainerReturnsTrainer(){
        Trainer trainer = new Trainer("Trainerone","Trainerone","trainerone");
        trainer.setUserId(1);
        Trainer result = trainerDao.selectTrainer(trainer);
        Assertions.assertInstanceOf(Trainer.class,result);
    }

    @Test
    void selectTrainerThrowsIllegalArgumentException(){
        Trainer trainer = new Trainer("Trainersix","Trainersix","trainersix");
        Assertions.assertThrows(IllegalArgumentException.class,()->trainerDao.selectTrainer(trainer));
    }
}
