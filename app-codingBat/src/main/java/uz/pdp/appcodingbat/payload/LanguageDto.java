package uz.pdp.appcodingbat.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageDto {

    @NotNull(message = "name should be not empity")
    private String name;

}
