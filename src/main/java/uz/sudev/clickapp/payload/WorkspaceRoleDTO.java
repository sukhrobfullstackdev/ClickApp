package uz.sudev.clickapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import uz.sudev.clickapp.entity.enums.WorkspaceRoleName;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class WorkspaceRoleDTO {
    private Long workspaceId;
    private String name;
    private WorkspaceRoleName extendsRole;
}
