package epam.projects.spring_core.DAOs;

import epam.projects.spring_core.models.Trainee;
import epam.projects.spring_core.models.Trainer;
import epam.projects.spring_core.models.Training;
import epam.projects.spring_core.storage.AppStorage;
import jakarta.annotation.Nonnull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class TrainingDaoTests {

    @MockBean
    AppStorage appStorage;

    TrainingDao trainingDao;

    @BeforeAll
    public void setup(){
        Map<String, Object> trainersMap = createTrainersMap();
        Mockito.when(this.appStorage.getNamespace("trainers")).thenReturn(trainersMap);
        Map<String, Object> traineeMap = new HashMap<>();
        Trainee trainee1 = new Trainee("Userone","Userone","userone");
        trainee1.setUserId(1);
        traineeMap.put("Userone.Userone",trainee1);
        Trainee trainee2 = new Trainee("Usertwo","Usertwo","usertwo");
        trainee2.setUserId(2);
        traineeMap.put("Usertwo.Usertwo",trainee2);
        Trainee trainee3 = new Trainee("Usertree","Usertree","usertree");
        trainee3.setUserId(3);
        traineeMap.put("Userthree.Userthree",trainee3);
        Trainee trainee4 = new Trainee("Userfour","Userfour","userfour");
        trainee4.setUserId(4);
        traineeMap.put("Userfour.Userfour", trainee4);
        Mockito.when(this.appStorage.getNamespace("trainees")).thenReturn(traineeMap);
        Map<String, Object> trainings = new HashMap<>();
        trainings.put("training1",new Training(1,1,1,"training1",new Date(),120));
        trainings.put("training2",new Training(2, 1,1,"training2",new Date(),120));
        trainings.put("training3",new Training(3,1,1,"training3",new Date(),120));
        trainings.put("training4",new Training(4, 1,1,"training4",new Date(),120));
        trainings.put("training5",new Training(5,1,1,"training5",new Date(),120));
        Mockito.when(appStorage.getNamespace("trainings")).thenReturn(trainings);
        trainingDao = new TrainingDao(appStorage);
    }

    @Nonnull
    private static Map<String, Object> createTrainersMap() {
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
        return trainersMap;
    }

    @Test
    void createTrainingReturnsTraining(){
        int trainingIdBefore = 0;
        Training training = new Training(trainingIdBefore,2,2,"training6",new Date(),120);
        Training newTraining = trainingDao.createTraining(training);
        Assertions.assertNotEquals(trainingIdBefore,newTraining.getTrainingId().getId());
    }

    @Test
    void createTrainingReturnsIllegalArgumentException(){
        Training training1 = new Training(0,6,6,"training6",new Date(),120);
        Training training2 = new Training(0,1,6,"training7",new Date(),120);
        Training training3 = new Training(0,2,6,"training8",new Date(),120);
        Assertions.assertAll(
                ()->assertThrows(IllegalArgumentException.class,()->trainingDao.createTraining(training1)),
                ()->assertThrows(IllegalArgumentException.class,()->trainingDao.createTraining(training2)),
                ()->assertThrows(IllegalArgumentException.class,()->trainingDao.createTraining(training3))
        );
    }

    @Test
    void selectTrainingReturnsTraining(){
        Training training = new Training(0,0,0,"training1",new Date(),120);
        Training newTraining = trainingDao.selectTraining(training);
        Assertions.assertNotEquals(training.getTrainingId(),newTraining.getTrainingId());
    }

    @Test
    void selectTrainingReturnsIllegalArgumentException(){
        Training training1 = new Training(0,0,0,"training7",new Date(),120);
        Assertions.assertThrows(IllegalArgumentException.class,()->trainingDao.selectTraining(training1));
    }


}
