package uz.pdp.appcodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.entity.Category;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.CategoryDto;
import uz.pdp.appcodingbat.service.CategoryService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /**
     * This is method returned ListCategory
     *
     * @return List
     */

    @GetMapping
    public ResponseEntity<List<Category>> getCategory() {
        List<Category> list = categoryService.getCategory();
        return ResponseEntity.ok(list);
    }

    /**
     * This is method returned Category
     *
     * @param id
     * @return Category
     */

    @GetMapping("/{id}")
    public ResponseEntity<Category> getByIdCategory(@PathVariable Integer id) {
        Category category = categoryService.getByIdCategory(id);
        return ResponseEntity.ok(category);
    }

    /**
     * This is method saved Category
     *
     * @param categoryDto
     * @return
     */

    @PostMapping
    public ResponseEntity<ApiResponse> savedCategory(@Valid @RequestBody CategoryDto categoryDto) {
        ApiResponse apiResponse = categoryService.savedCategory(categoryDto);
        if (apiResponse.isSuccsess()) {
            return ResponseEntity.status(201).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }

    /**
     * This is method edited Category
     *
     * @param id
     * @param categoryDto
     * @return
     */

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editedCategory(@PathVariable Integer id, @Valid @RequestBody CategoryDto categoryDto) {
        ApiResponse apiResponse = categoryService.editCotegory(id, categoryDto);
        if (apiResponse.isSuccsess()) {
            return ResponseEntity.status(202).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }

    /**
     * This is method deleted Category
     *
     * @param id
     * @return ApiReponsa
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletedCategory(@PathVariable Integer id) {
        ApiResponse apiResponse = categoryService.deletedCategory(id);
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
