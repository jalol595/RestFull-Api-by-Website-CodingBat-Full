package uz.pdp.appcodingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appcodingbat.entity.Language;

import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    @NotNull(message = "name should be not empity")
    private String name;

    @NotNull(message = "description should be not empity")
    private String description;

    @NotNull(message = "languagesId should be not empity")
    private List<Integer> languagesId;
}
