package uz.pdp.appcodingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appcodingbat.entity.Tasks;
import uz.pdp.appcodingbat.entity.User;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnwersDto {

    @NotNull(message = "text should be not empity")
    private String text;

    @NotNull(message = "taskId should be not empity")
    private Integer tasksId;

    @NotNull(message = "userId should be not empity")
    private Integer userId;

}
