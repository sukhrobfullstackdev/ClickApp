package uz.sudev.clickapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import uz.sudev.clickapp.entity.enums.WorkspacePermissionName;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class WorkspacePermissionDTO {
    private UUID workspaceRoleId;
    private List<WorkspacePermissionName> permissions;
}
