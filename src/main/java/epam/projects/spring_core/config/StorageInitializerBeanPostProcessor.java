package epam.projects.spring_core.config;

import epam.projects.spring_core.models.*;
import epam.projects.spring_core.storage.AppStorage;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

@Component
public class StorageInitializerBeanPostProcessor implements BeanPostProcessor {

    @Value("classpath:objects.json")
    private Resource dataFileResource;

    @Override
    public Object postProcessBeforeInitialization(@Nonnull Object bean, @Nonnull String beanName) {
        if (bean instanceof AppStorage commonStorage) {

            ObjectMapper objectMapper = new ObjectMapper();
            ArrayList<Map<String, Object>> rawDataList;
            try {
                rawDataList = objectMapper.readValue(
                        dataFileResource.getInputStream(),
                        ArrayList.class
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            for (Map<String, Object> item : rawDataList) {
                String group = (String) item.get("group");
                int id = 0;
                TrainingId id1 = null;
                try {
                    id = Integer.parseInt(item.get("id").toString());
                } catch (NumberFormatException ex) {
                    id1 = new TrainingId(Integer.parseInt(item.get("traineeId").toString()), Integer.parseInt(item.get("trainerId").toString()));
                }

                if ("trainees".equalsIgnoreCase(group)) {
                    Trainee trainee = new Trainee();
                    trainee.setUserId(id);
                    trainee.setFirstName((String) item.get("firstName"));
                    trainee.setLastName((String) item.get("lastName"));
                    trainee.setUsername((String) item.get("username"));
                    trainee.setPassword((String) item.get("password"));

                    Object isActive = item.get("isActive");
                    trainee.setActive(isActive instanceof Boolean ? (Boolean) isActive : Boolean.parseBoolean(String.valueOf(isActive)));

                    String dobStr = (String) item.get("dateOfBirth");
                    if (dobStr != null) {
                        try {
                            trainee.setDateOfBirth(dateFormat.parse(dobStr));
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    trainee.setAddress((String) item.get("address"));

                    commonStorage.getNamespace("trainees").put(trainee.getUsername(), trainee);

                } else if ("trainers".equalsIgnoreCase(group)) {
                    Trainer trainer = new Trainer();
                    trainer.setUserId(id);
                    trainer.setFirstName((String) item.get("firstName"));
                    trainer.setLastName((String) item.get("lastName"));
                    trainer.setUsername((String) item.get("username"));
                    trainer.setPassword((String) item.get("password"));

                    Object isActive = item.get("isActive");
                    trainer.setActive(isActive instanceof Boolean ? (Boolean) isActive : Boolean.parseBoolean(String.valueOf(isActive)));
                    trainer.setSpecialization((String) item.get("specialization"));

                    commonStorage.getNamespace("trainers").put(trainer.getUsername(), trainer);

                } else if ("trainings".equalsIgnoreCase(group)) {
                    Training training = new Training();
                    training.setTrainingId(id1);

                    Object traineeIdObj = item.get("traineeId");
                    Object trainerIdObj = item.get("trainerId");
                    if (trainerIdObj != null)
                        training.setTrainingId(new TrainingId(((Number) traineeIdObj).intValue(), ((Number) trainerIdObj).intValue()));

                    training.setTrainingName((String) item.get("trainingName"));
                    training.setTrainingType(new TrainingType((String) item.get("trainingType")));

                    String dateStr = (String) item.get("trainingDate");
                    if (dateStr != null) {

                        try {
                            training.setTrainingDate(dateTimeFormat.parse(dateStr));
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }

                    }

                    Object durationObj = item.get("trainingDuration");
                    if (durationObj != null) {
                        training.setTrainingDuration(((Number) durationObj).intValue());
                    }

                    commonStorage.getNamespace("trainings").put(training.getTrainingName(), training);
                }
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(@Nonnull Object bean, @Nonnull String beanName) {
        return bean;
    }
}
