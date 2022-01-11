package uz.pdp.appcodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.entity.User;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.UserDto;
import uz.pdp.appcodingbat.service.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;


    /**
     * This is method returned ListUser
     *
     * @return List
     */

    @GetMapping
    public ResponseEntity<List<User>> getUser() {
        List<User> user = userService.getUser();
        return ResponseEntity.ok(user);
    }

    /**
     * This is method returned User by id
     *
     * @param id
     * @return User
     */

    @GetMapping("/{id}")
    public ResponseEntity<User> getByIdUser(@PathVariable Integer id) {
        User serviceByIdUser = userService.getByIdUser(id);
        return ResponseEntity.ok(serviceByIdUser);
    }

    /**
     * This is method saved User
     *
     * @param userDto
     * @return
     */

    @PostMapping
    public ResponseEntity<ApiResponse> saveUser(@Valid @RequestBody UserDto userDto) {
        ApiResponse apiResponse = userService.saveUser(userDto);
        if (apiResponse.isSuccsess()) {
            return ResponseEntity.status(201).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }

    /**
     * This is method edited User
     *
     * @param id
     * @param userDto
     * @return
     */

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editUser(@PathVariable Integer id, @Valid @RequestBody UserDto userDto) {
        ApiResponse apiResponse = userService.editUser(id, userDto);
        if (apiResponse.isSuccsess()) {
            return ResponseEntity.status(202).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);

    }

    /**
     * This is method deleted User
     *
     * @param id
     * @return
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id) {
        ApiResponse apiResponse = userService.deleteUser(id);
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
