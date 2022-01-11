package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appcodingbat.entity.Language;
import uz.pdp.appcodingbat.entity.Tasks;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.TasksDto;
import uz.pdp.appcodingbat.repository.LanguageRepository;
import uz.pdp.appcodingbat.repository.TasksRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class TasksService {

    @Autowired
    TasksRepository tasksRepository;

    @Autowired
    LanguageRepository languageRepository;

    /**
     * This is method returned ListTasks
     *
     * @return ListTasks
     */

    public List<Tasks> getTasks() {
        List<Tasks> tasksList = tasksRepository.findAll();
        return tasksList;
    }

    /**
     * This is method returned Tasks by id
     *
     * @param id
     * @return Tasks
     */

    public Tasks getByIdTasks(Integer id) {
        Optional<Tasks> optionalTasks = tasksRepository.findById(id);
        return optionalTasks.orElse(null);
    }

    /**
     * This is method saved Tasks
     *
     * @param tasksDto
     * @return ApiRespons
     */

    public ApiResponse saveTasks(TasksDto tasksDto) {
        boolean exists =
                tasksRepository.existsByNameAndSolutionAndHintAndMethodAndHasStar
                        (tasksDto.getName(), tasksDto.getSolution(), tasksDto.getHint(),
                                tasksDto.getMethod(), tasksDto.getHasStar());

        boolean existsById = languageRepository.existsById(tasksDto.getLanguageId());
        if (exists && existsById) return new ApiResponse("Already exist", false);

        Optional<Language> optionalLanguage = languageRepository.findById(tasksDto.getLanguageId());
        Language language = optionalLanguage.get();

        Tasks tasks = new Tasks();
        tasks.setName(tasksDto.getName());
        tasks.setSolution(tasks.getSolution());
        tasks.setHint(tasksDto.getHint());
        tasks.setMethod(tasksDto.getMethod());
        tasks.setHasStar(tasksDto.getHasStar());
        tasks.setLanguage(language);
        tasksRepository.save(tasks);
        return new ApiResponse("Succsessfull saved", true);

    }

    /**
     * This is method edited Tasks
     *
     * @param id
     * @param tasksDto
     * @return
     */

    public ApiResponse editTasks(Integer id, TasksDto tasksDto) {
        boolean exists =
                tasksRepository.existsByNameAndSolutionAndHintNotAndMethodAndHasStarNot
                        (tasksDto.getName(), tasksDto.getSolution(), tasksDto.getHint(),
                                tasksDto.getMethod(), tasksDto.getHasStar());

        boolean existsById = languageRepository.existsById(tasksDto.getLanguageId());
        if (exists && existsById) return new ApiResponse("There is such a tasks", false);

        Optional<Language> optionalLanguage = languageRepository.findById(tasksDto.getLanguageId());
        Language language = optionalLanguage.get();

        Tasks tasks = new Tasks();
        tasks.setName(tasksDto.getName());
        tasks.setSolution(tasks.getSolution());
        tasks.setHint(tasksDto.getHint());
        tasks.setMethod(tasksDto.getMethod());
        tasks.setHasStar(tasksDto.getHasStar());
        tasks.setLanguage(language);
        tasksRepository.save(tasks);
        return new ApiResponse("Succsessfull edited", true);
    }

    /**
     * This is method deleted Tasks
     *
     * @param id
     * @return
     */

    public ApiResponse deleteTasks(Integer id) {
        try {
            tasksRepository.deleteById(id);
            return new ApiResponse("Succsessfull deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error!!!", false);
        }
    }

}
