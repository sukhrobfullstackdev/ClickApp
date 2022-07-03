package uz.sudev.clickapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkspaceDTO {
    @NotBlank(message = "Please enter the name of workspace!")
    private String name;
    @NotBlank(message = "Please enter the main color of workspace!")
    private String color;
    private UUID avatarId;
}
