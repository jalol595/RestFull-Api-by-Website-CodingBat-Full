package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import uz.pdp.appcodingbat.entity.Category;
import uz.pdp.appcodingbat.entity.Language;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.CategoryDto;
import uz.pdp.appcodingbat.repository.CategoryRepository;
import uz.pdp.appcodingbat.repository.LanguageRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    LanguageRepository languageRepository;

    /**
     * This is method returned ListCategory
     *
     * @return List
     */

    public List<Category> getCategory() {
        List<Category> list = categoryRepository.findAll();
        return list;
    }

    /**
     * This is method returned Category
     *
     * @param id
     * @return Category
     */

    public Category getByIdCategory(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    /**
     * This is method saved Category
     *
     * @param categoryDto
     * @return
     */

    public ApiResponse savedCategory(CategoryDto categoryDto) {
        boolean exists = categoryRepository.existsByNameAndDescription(categoryDto.getName(), categoryDto.getDescription());
        if (exists) return new ApiResponse("Already exist", false);

        List<Language> languageList = languageRepository.findAllById(categoryDto.getLanguagesId());

        Category category = new Category();
        category.setName(category.getName());
        category.setDescription(category.getDescription());
        category.setLanguageList(languageList);
        categoryRepository.save(category);
        return new ApiResponse("Succsessfull saved", true);
    }

    /**
     * This is method edited Category
     *
     * @param id
     * @param categoryDto
     * @return
     */

    public ApiResponse editCotegory(Integer id, CategoryDto categoryDto) {
        boolean exists = categoryRepository.existsByNameAndDescription(categoryDto.getName(), categoryDto.getDescription());
        if (exists) return new ApiResponse("There is such a category", false);

        List<Language> languageList = languageRepository.findAllById(categoryDto.getLanguagesId());

        Category category = new Category();
        category.setName(category.getName());
        category.setDescription(category.getDescription());
        category.setLanguageList(languageList);
        categoryRepository.save(category);
        return new ApiResponse("Succsessfull edit", true);

    }

    /**
     * This is method deleted Category
     *
     * @param id
     * @return ApiReponsa
     */

    public ApiResponse deletedCategory(Integer id) {
        try {
            categoryRepository.deleteById(id);
            return new ApiResponse("Succsessfull deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error!!!", false);
        }
    }


}