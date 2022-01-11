package uz.pdp.appcodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcodingbat.entity.Tasks;

public interface TasksRepository extends JpaRepository<Tasks, Integer> {
    boolean existsByNameAndSolutionAndHintAndMethodAndHasStar(String name, String solution, String hint, String method, String hasStar);
    boolean existsByNameAndSolutionAndHintNotAndMethodAndHasStarNot(String name, String solution, String hint, String method, String hasStar);
}
