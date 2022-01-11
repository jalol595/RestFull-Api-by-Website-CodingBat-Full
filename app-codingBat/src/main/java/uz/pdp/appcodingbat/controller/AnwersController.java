package uz.pdp.appcodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.entity.Anwers;
import uz.pdp.appcodingbat.payload.AnwersDto;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.service.AnwersService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/anwers")
public class AnwersController {

    @Autowired
    AnwersService anwersService;

    /**
     * This is method returned ListAnwers
     *
     * @return List
     */

    @GetMapping
    public ResponseEntity<List<Anwers>> getAnwers() {
        List<Anwers> anwers = anwersService.getAnwers();
        return ResponseEntity.ok(anwers);
    }

    /**
     * This is method returned Anwers
     *
     * @param id
     * @return Anwers
     */

    @GetMapping("/{id}")
    public ResponseEntity<Anwers> getByIdAnwers(@PathVariable Integer id) {
        Anwers anwers = anwersService.getByIdAnwers(id);
        return ResponseEntity.ok(anwers);
    }

    /**
     * This is method saved Anwers
     *
     * @param anwersDto
     * @return ApiResponse
     */

    @PostMapping
    public ResponseEntity<ApiResponse> savedAnwers(@Valid @RequestBody AnwersDto anwersDto) {
        ApiResponse apiResponse = anwersService.savedAnwers(anwersDto);
        if (apiResponse.isSuccsess()) {
            return ResponseEntity.status(201).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }


    /**
     * This is method edited Anwers
     *
     * @param id
     * @param anwersDto
     * @return ApiResponse
     */

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editedAnwers(@PathVariable Integer id, @Valid @RequestBody AnwersDto anwersDto) {
        ApiResponse apiResponse = anwersService.editedAnwers(id, anwersDto);
        if (apiResponse.isSuccsess()) {
            return ResponseEntity.status(204).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }

    /**
     * This is method deleted Anwers
     *
     * @param id
     * @return ApiResponse
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletedAnwers(@PathVariable Integer id) {
        ApiResponse apiResponse = anwersService.deletedAnwers(id);
        if (apiResponse.isSuccsess()) {
            return ResponseEntity.status(205).body(apiResponse);
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
