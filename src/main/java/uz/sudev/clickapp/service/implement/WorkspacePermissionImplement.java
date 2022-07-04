package uz.sudev.clickapp.service.implement;

import org.springframework.http.ResponseEntity;
import uz.sudev.clickapp.entity.WorkspacePermission;

import java.util.List;
import java.util.UUID;

public interface WorkspacePermissionImplement {
    ResponseEntity<List<WorkspacePermission>> getAllPermissions(UUID workspaceId);
}
