package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appcodingbat.entity.Language;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.LanguageDto;
import uz.pdp.appcodingbat.repository.LanguageRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    @Autowired
    LanguageRepository languageRepository;

    /**
     * This is method returned ListLanguagr
     *
     * @return ListLanguagr
     */

    public List<Language> getLanguage() {
        List<Language> languages = languageRepository.findAll();
        return languages;
    }

    /**
     * This is method returned Languagr by id
     *
     * @return Languagr
     */

    public Language getByIdLanguage(Integer id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        return optionalLanguage.orElse(null);

    }

    /**
     * This is method saved ApiResponse
     *
     * @param languageDto
     * @return ApiRespons
     */

    public ApiResponse saveLanguage(@RequestBody LanguageDto languageDto) {
        boolean name = languageRepository.existsByName(languageDto.getName());
        if (name) return new ApiResponse("Already exist", false);

        Language language = new Language(null, languageDto.getName());
        languageRepository.save(language);
        return new ApiResponse("Succsessfull saved", true);
    }

    /**
     * This is method edited ApiResponse
     *
     * @param id
     * @param languageDto
     * @return
     */

    public ApiResponse editLanguage(Integer id, LanguageDto languageDto) {
        boolean name = languageRepository.existsByName(languageDto.getName());
        if (name) return new ApiResponse("There is such a name", false);


        Language language = new Language(null, languageDto.getName());
        languageRepository.save(language);
        return new ApiResponse("Succsessfull edited", true);
    }

    /**
     * This is method deleted ApiResponse
     *
     * @param id
     * @return
     */

    public ApiResponse deletLanguage(Integer id) {
        try {
            languageRepository.deleteById(id);
            return new ApiResponse("Succsessfull deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error!!!", false);
        }
    }


}
