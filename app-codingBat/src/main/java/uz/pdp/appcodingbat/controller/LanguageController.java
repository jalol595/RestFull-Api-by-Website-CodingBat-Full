package uz.pdp.appcodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.entity.Language;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.LanguageDto;
import uz.pdp.appcodingbat.service.LanguageService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/language")
public class LanguageController {

    @Autowired
    LanguageService languageService;

    /**
     * This is method returned ListLanguagr
     *
     * @return ListLanguagr
     */

    @GetMapping
    public ResponseEntity<List<Language>> getLanguage() {
        List<Language> languageList = languageService.getLanguage();
        return ResponseEntity.ok(languageList);
    }


    /**
     * This is method returned Languagr by id
     *
     * @return Language
     */

    @GetMapping("/{id}")
    public ResponseEntity<Language> getByIdLanguage(@PathVariable Integer id) {
        Language language = languageService.getByIdLanguage(id);
        return ResponseEntity.ok(language);
    }

    /**
     * This is method saved ApiResponse
     *
     * @param languageDto
     * @return ApiRespons
     */

    @PostMapping
    public ResponseEntity<ApiResponse> saveLanguage(@Valid @RequestBody LanguageDto languageDto) {
        ApiResponse apiResponse = languageService.saveLanguage(languageDto);
        if (apiResponse.isSuccsess()) {
            return ResponseEntity.status(201).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }

    /**
     * This is method returned edited
     *
     * @param id
     * @param languageDto
     * @return
     */

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editLanguage(@PathVariable Integer id, @Valid @RequestBody LanguageDto languageDto) {
        ApiResponse apiResponse = languageService.editLanguage(id, languageDto);
        if (apiResponse.isSuccsess()) {
            return ResponseEntity.status(202).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }

    /**
     * This is method deleted ApiResponse
     *
     * @param id
     * @return
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletLanguage(@PathVariable Integer id) {
        ApiResponse apiResponse = languageService.deletLanguage(id);
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
