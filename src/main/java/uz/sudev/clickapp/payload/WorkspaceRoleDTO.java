package uz.sudev.clickapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import uz.sudev.clickapp.entity.enums.AddEditRemove;
import uz.sudev.clickapp.entity.enums.WorkspacePermissionName;
import uz.sudev.clickapp.entity.enums.WorkspaceRoleName;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class WorkspaceRoleDTO {
    private UUID id;
    private String name;
    private Long workspaceId;
    private WorkspaceRoleName extendsRole;
    private WorkspacePermissionName workspacePermissionName;
    private AddEditRemove addEditRemove;
}
