package uz.pdp.appcodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.entity.Tasks;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.TasksDto;
import uz.pdp.appcodingbat.service.TasksService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TasksController {


    @Autowired
    TasksService tasksService;


    /**
     * This is method returned ListTasks
     *
     * @return ListTasks
     */

    @GetMapping
    public ResponseEntity<List<Tasks>> getTasks() {
        List<Tasks> tasksList = tasksService.getTasks();
        return ResponseEntity.ok(tasksList);
    }

    /**
     * This is method returned Tasks by id
     *
     * @param id
     * @return Tasks
     */

    @GetMapping("/{id}")
    public ResponseEntity<Tasks> getByIdTasks(@PathVariable Integer id) {
        Tasks serviceByIdTasks = tasksService.getByIdTasks(id);
        return ResponseEntity.ok(serviceByIdTasks);
    }

    /**
     * This is method saved Tasks
     *
     * @param tasksDto
     * @return ApiRespons
     */

    @PostMapping
    public ResponseEntity<ApiResponse> saveTasks(@Valid @RequestBody TasksDto tasksDto) {
        ApiResponse apiResponse = tasksService.saveTasks(tasksDto);
        if (apiResponse.isSuccsess()) {
            return ResponseEntity.status(201).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }

    /**
     * This is method edited Tasks
     *
     * @param id
     * @param tasksDto
     * @return
     */

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editTasks(@PathVariable Integer id, @Valid @RequestBody TasksDto tasksDto) {
        ApiResponse apiResponse = tasksService.editTasks(id, tasksDto);
        if (apiResponse.isSuccsess()) {
            return ResponseEntity.status(202).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }

    /**
     * This is method deleted Tasks
     *
     * @param id
     * @return
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTasks(@PathVariable Integer id) {
        ApiResponse apiResponse = tasksService.deleteTasks(id);
        if (apiResponse.isSuccsess()) {
            return ResponseEntity.status(204).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
