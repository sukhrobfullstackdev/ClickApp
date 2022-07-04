package uz.sudev.clickapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import uz.sudev.clickapp.entity.enums.TaskPermission;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class ProjectUserDTO {
    private Long projectId;
    private UUID userId;
    private TaskPermission taskPermission;
}
