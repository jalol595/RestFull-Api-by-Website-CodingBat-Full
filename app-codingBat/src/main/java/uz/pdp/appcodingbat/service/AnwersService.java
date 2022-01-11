package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appcodingbat.entity.Anwers;
import uz.pdp.appcodingbat.entity.Tasks;
import uz.pdp.appcodingbat.entity.User;
import uz.pdp.appcodingbat.payload.AnwersDto;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.repository.AnwersRepository;
import uz.pdp.appcodingbat.repository.TasksRepository;
import uz.pdp.appcodingbat.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class AnwersService {

    @Autowired
    AnwersRepository anwersRepository;

    @Autowired
    TasksRepository tasksRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * This is method returned ListAnwers
     *
     * @return List
     */

    public List<Anwers> getAnwers() {
        List<Anwers> list = anwersRepository.findAll();
        return list;
    }

    /**
     * This is method returned Anwers
     *
     * @param id
     * @return Anwers
     */

    public Anwers getByIdAnwers(Integer id) {
        Optional<Anwers> anwers = anwersRepository.findById(id);
        return anwers.orElse(null);
    }

    /**
     * This is method saved Anwers
     *
     * @param anwersDto
     * @return ApiResponse
     */

    public ApiResponse savedAnwers(AnwersDto anwersDto) {
        boolean text = anwersRepository.existsByText(anwersDto.getText());
        boolean byId = tasksRepository.existsById(anwersDto.getTasksId());
        boolean exists = userRepository.existsById(anwersDto.getUserId());
        if (text && byId && exists) return new ApiResponse("Already exist", false);

        Optional<Tasks> optionalTasks = tasksRepository.findById(anwersDto.getTasksId());
        Tasks tasks = optionalTasks.get();

        Optional<User> userOptional = userRepository.findById(anwersDto.getUserId());
        User user = userOptional.get();

        Anwers anwers = new Anwers(null, anwersDto.getText(), tasks, user);
        anwersRepository.save(anwers);
        return new ApiResponse("Succsessfull saved", true);
    }

    /**
     * This is method edited Anwers
     *
     * @param id
     * @param anwersDto
     * @return ApiResponse
     */

    public ApiResponse editedAnwers(Integer id, AnwersDto anwersDto) {
        boolean text = anwersRepository.existsByText(anwersDto.getText());
        if (text) return new ApiResponse("There is such text", false);

        Optional<Tasks> optionalTasks = tasksRepository.findById(anwersDto.getTasksId());
        Tasks tasks = optionalTasks.get();

        Optional<User> userOptional = userRepository.findById(anwersDto.getUserId());
        User user = userOptional.get();

        Anwers anwers = new Anwers(null, anwersDto.getText(), tasks, user);
        anwersRepository.save(anwers);
        return new ApiResponse("Succsessfull edited", true);
    }

    /**
     * This is method deleted Anwers
     *
     * @param id
     * @return ApiResponse
     */

    public ApiResponse deletedAnwers(Integer id) {
        try {
            anwersRepository.deleteById(id);
            return new ApiResponse("Succsessfull deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error!!!", false);

        }
    }


}
