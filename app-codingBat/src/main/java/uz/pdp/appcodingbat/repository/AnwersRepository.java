package uz.pdp.appcodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcodingbat.entity.Anwers;

public interface AnwersRepository extends JpaRepository<Anwers, Integer> {
    boolean existsByText(String text);
}
