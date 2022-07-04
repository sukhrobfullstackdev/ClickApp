package uz.sudev.clickapp.service.implement;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.sudev.clickapp.entity.User;
import uz.sudev.clickapp.entity.WorkspaceRole;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.WorkspaceRoleDTO;

import java.util.UUID;

public interface WorkspaceRoleImplement {
    ResponseEntity<Message> addRole(WorkspaceRoleDTO workspaceRoleDTO);

    ResponseEntity<Message> editRole(UUID id, WorkspaceRoleDTO workspaceRoleDTO);

    ResponseEntity<Message> deleteRole(UUID id);

    ResponseEntity<Page<WorkspaceRole>> getRoles(int page, int size, Long workspaceId);

    ResponseEntity<WorkspaceRole> getRole(Long workspaceId, UUID roleId);
}
