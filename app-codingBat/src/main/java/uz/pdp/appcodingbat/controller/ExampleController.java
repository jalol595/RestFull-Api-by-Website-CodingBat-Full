package uz.pdp.appcodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.entity.Example;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.ExampleDto;
import uz.pdp.appcodingbat.service.ExampleService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/example")
public class ExampleController {

    @Autowired
    ExampleService exampleService;


    /**
     * This is method returned Example List
     *
     * @param
     * @return List
     */

    public ResponseEntity<List<Example>> getExampleCont(){
        List<Example> exampleService = this.exampleService.getExampleService();
        return ResponseEntity.ok(exampleService);
    }

    /**
     * This is method returned Example
     *
     * @param id
     * @return Example
     */

    @GetMapping
    public ResponseEntity<Example> getByIdExample(@PathVariable Integer id) {
        Example example = exampleService.getByIdExample(id);
        return ResponseEntity.ok(example);
    }

    /**
     * This is method saved Example
     *
     * @param exampleDto
     * @return ApiResponse
     */


    @PostMapping
    public ResponseEntity<ApiResponse> savedExample(@Valid @RequestBody ExampleDto exampleDto) {
        ApiResponse apiResponse = exampleService.savedExample(exampleDto);
        if (apiResponse.isSuccsess()) {
            return ResponseEntity.status(201).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }

    /**
     * his is method edited Example
     *
     * @param id
     * @param exampleDto
     * @return ApiResponse
     */

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editedExample(@PathVariable Integer id, @Valid @RequestBody ExampleDto exampleDto) {
        ApiResponse apiResponse = exampleService.editedExample(id, exampleDto);
        if (apiResponse.isSuccsess()) {
            return ResponseEntity.status(202).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }


    /**
     * This is method deleted Example
     *
     * @param id
     * @return ApiResponse
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletedExample(@PathVariable Integer id) {
        ApiResponse apiResponse = exampleService.deletedExample(id);
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
