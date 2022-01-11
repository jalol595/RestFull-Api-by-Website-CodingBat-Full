package uz.pdp.appcodingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appcodingbat.entity.Language;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TasksDto {


    @NotNull(message = "name should be not empity")
    private String name;

    @NotNull(message = "solution should be not empity")
    private String solution;

    @NotNull(message = "hint should be not empity")
    private String hint;

    @NotNull(message = "method should be not empity")
    private String method;

    @NotNull(message = "hasstar should be not empity")
    private String hasStar;

    @NotNull(message = "languageId should be not empity")
    private Integer languageId;

}
