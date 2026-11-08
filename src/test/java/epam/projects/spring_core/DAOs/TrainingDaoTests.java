package epam.projects.spring_core.DAOs;

import epam.projects.spring_core.models.Trainee;
import epam.projects.spring_core.models.Trainer;
import epam.projects.spring_core.models.Training;
import epam.projects.spring_core.storage.AppStorage;
import jakarta.annotation.Nonnull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        traineeMap.put("Userone.Userone",new Trainee("Userone","Userone","userone"));
        traineeMap.put("Usertwo.Usertwo",new Trainee("Usertwo","Usertwo","usertwo"));
        traineeMap.put("Userthree.Userthree",new Trainee("Usertree","Usertree","usertree"));
        traineeMap.put("Userfour.Userfour", new Trainee("Userfour","Userfour","userfour"));
        Mockito.when(this.appStorage.getNamespace("trainees")).thenReturn(traineeMap);
        Map<String, Object> trainings = new HashMap<>();
        trainings.put("training1",new Training(1,1,"training1",new Date(),120));
        trainings.put("training2",new Training(1,1,"training2",new Date(),120));
        trainings.put("training3",new Training(1,1,"training3",new Date(),120));
        trainings.put("training4",new Training(1,1,"training4",new Date(),120));
        trainings.put("training5",new Training(1,1,"training5",new Date(),120));
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

    }


}
