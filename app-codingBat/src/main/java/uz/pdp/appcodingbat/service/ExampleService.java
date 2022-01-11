package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import uz.pdp.appcodingbat.entity.Example;
import uz.pdp.appcodingbat.entity.Tasks;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.ExampleDto;
import uz.pdp.appcodingbat.repository.ExampleRepository;
import uz.pdp.appcodingbat.repository.TasksRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleService {

    @Autowired
    ExampleRepository exampleRepository;

    @Autowired
    TasksRepository tasksRepository;

    /**
     * This is method returned Example List
     *
     * @param
     * @return List
     */

    @GetMapping
    public List<Example> getExampleService() {
        List<Example> list = exampleRepository.findAll();
        return list;
    }


    /**
     * This is method returned Example
     *
     * @param id
     * @return Example
     */

    public Example getByIdExample(Integer id) {
        Optional<Example> byId = exampleRepository.findById(id);
        return byId.orElse(null);
    }

    /**
     * This is method saved Example
     *
     * @param exampleDto
     * @return ApiResponse
     */

    public ApiResponse savedExample(ExampleDto exampleDto) {
        boolean text = exampleRepository.existsByText(exampleDto.getText());
        boolean id = tasksRepository.existsById(exampleDto.getTasksId());
        if (text && id) return new ApiResponse("Already exist", false);

        Optional<Tasks> optionalTasks = tasksRepository.findById(exampleDto.getTasksId());
        Tasks tasks = optionalTasks.get();

        Example example = new Example(null, exampleDto.getText(), tasks);
        exampleRepository.save(example);
        return new ApiResponse("Succsessfull saved", true);

    }

    /**
     * This is method edited Example
     *
     * @param id
     * @param exampleDto
     * @return ApiResponse
     */

    public ApiResponse editedExample(Integer id, ExampleDto exampleDto) {
        boolean exists = exampleRepository.existsByText(exampleDto.getText());
        if (exists) return new ApiResponse("There is such a text", false);

        Optional<Tasks> optionalTasks = tasksRepository.findById(exampleDto.getTasksId());
        Tasks tasks = optionalTasks.get();

        Example example = new Example(null, exampleDto.getText(), tasks);
        exampleRepository.save(example);
        return new ApiResponse("Succsessfull editved", true);
    }

    /**
     * This is method deleted Example
     *
     * @param id
     * @return ApiResponse
     */

    public ApiResponse deletedExample(@PathVariable Integer id) {
        try {
            exampleRepository.deleteById(id);
            return new ApiResponse("Succsessfull deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error!!!", false);
        }
    }

}



