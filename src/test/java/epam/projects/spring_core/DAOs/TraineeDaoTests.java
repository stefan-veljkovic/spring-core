package epam.projects.spring_core.DAOs;

import epam.projects.spring_core.models.Trainee;
import epam.projects.spring_core.storage.AppStorage;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TraineeDaoTests {

    @MockBean
    public AppStorage appStorage;

    public TraineeDao traineeDao;

    @BeforeAll
    void setUp() {
        Map<String, Object> traineeMap = new HashMap<>();
        traineeMap.put("Userone.Userone",new Trainee("Userone","Userone","userone"));
        traineeMap.put("Usertwo.Usertwo",new Trainee("Usertwo","Usertwo","usertwo"));
        traineeMap.put("Userthree.Userthree",new Trainee("Usertree","Usertree","usertree"));
        traineeMap.put("Userfour.Userfour", new Trainee("Userfour","Userfour","userfour"));
        Mockito.when(this.appStorage.getNamespace("trainees")).thenReturn(traineeMap);
        traineeDao = new TraineeDao(appStorage);
    }

    @Test
    public void createTraineeReturnsTrainee() {
        Trainee result = traineeDao.createTrainee(new Trainee("Userfive","Userfive","userfive"));
        Assertions.assertNotNull(result.getUserId());
    }

    @Test
    public void getTraineeThrowsIllegalArgumentException() {
        Assertions.assertAll(
                ()-> assertThrows(IllegalArgumentException.class, () -> traineeDao.createTrainee(new Trainee(null,"Userfive","userfive"))),
                ()-> assertThrows(IllegalArgumentException.class, () -> traineeDao.createTrainee(new Trainee("Userfive",null,"userfive"))),
                ()-> assertThrows(IllegalArgumentException.class, () -> traineeDao.createTrainee(new Trainee("Userfive","Userfive",null)))
        );
    }

    @Test
    public void updateTraineeReturnsTrainee(){
        Trainee newTrainee1 = new Trainee("Userone","Userone","userone");
        newTrainee1.setUserId(1);
        traineeDao.updateTrainee(newTrainee1);
        Assertions.assertEquals(newTrainee1.getAddress(),traineeDao.selectTrainee(newTrainee1).getAddress());
    }

    @Test
    public void updateTraineeReturnsIllegalArgumentException(){
        Trainee newTrainee1 = new Trainee("Usersix","Usersix","usersix");
        newTrainee1.setUserId(6);
        Assertions.assertThrows(IllegalArgumentException.class, () -> traineeDao.updateTrainee(newTrainee1));
    }

    @Test
    public void deleteTraineeDoesntThrowException(){
        Trainee newTrainee1 = new Trainee("Userone","Userone","userone");
        newTrainee1.setUserId(1);
        Assertions.assertDoesNotThrow(()->traineeDao.deleteTrainee(newTrainee1));
    }

    @Test
    public void deleteTraineeThrowsIllegalArgumentException(){
        Trainee newTrainee1 = new Trainee("Usersix","Usersix","usersix");
        newTrainee1.setUserId(6);
        Assertions.assertThrows(IllegalArgumentException.class, () -> traineeDao.deleteTrainee(newTrainee1));
    }
}
