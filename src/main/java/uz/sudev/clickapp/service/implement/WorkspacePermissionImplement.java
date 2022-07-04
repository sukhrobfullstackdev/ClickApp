package uz.sudev.clickapp.service.implement;

import org.springframework.http.ResponseEntity;
import uz.sudev.clickapp.entity.WorkspacePermission;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.WorkspacePermissionDTO;

import java.util.List;
import java.util.UUID;

public interface WorkspacePermissionImplement {
    ResponseEntity<List<WorkspacePermission>> getAllPermissions(UUID workspaceId);

    ResponseEntity<Message> addPermissionsToRole(UUID workspaceRoleId, WorkspacePermissionDTO workspacePermissionDTO);

    ResponseEntity<Message> editPermissionsToRole(UUID workspaceRoleId, WorkspacePermissionDTO workspacePermissionDTO);

    ResponseEntity<Message> deletePermissionsToRole(UUID workspaceRoleId);
}
