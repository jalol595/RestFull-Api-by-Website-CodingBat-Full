package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appcodingbat.entity.User;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.UserDto;
import uz.pdp.appcodingbat.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * This is method returned ListUser
     *
     * @return List
     */

    public List<User> getUser() {
        List<User> list = userRepository.findAll();
        return list;
    }

    /**
     * This is method returned User by id
     *
     * @param id
     * @return User
     */

    public User getByIdUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    /**
     * This is method saved User
     *
     * @param userDto
     * @return
     */

    public ApiResponse saveUser(UserDto userDto) {
        boolean exists = userRepository.existsByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
        if (exists) return new ApiResponse("Already exist", false);

        User user = new User(null, userDto.getEmail(), userDto.getPassword());
        userRepository.save(user);
        return new ApiResponse("Succsessfull saved", true);
    }

    /**
     * This is method edited User
     *
     * @param id
     * @param userDto
     * @return
     */

    public ApiResponse editUser(Integer id, UserDto userDto) {
        boolean exists = userRepository.existsByEmailAndPasswordNot(userDto.getEmail(), userDto.getPassword());
        if (exists) return new ApiResponse("There is such email", false);

        User user = new User(null, userDto.getEmail(), userDto.getPassword());
        userRepository.save(user);
        return new ApiResponse("Succsessfull edited", true);

    }

    /**
     * This is method deleted User
     *
     * @param id
     * @return
     */

    public ApiResponse deleteUser(Integer id) {
        try {
            userRepository.deleteById(id);
            return new ApiResponse("Succsessfull deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error", false);
        }
    }

}
