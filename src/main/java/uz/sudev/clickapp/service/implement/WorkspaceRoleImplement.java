package uz.sudev.clickapp.service.implement;

import org.springframework.http.ResponseEntity;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.WorkspaceRoleDTO;

public interface WorkspaceRoleImplement {
    ResponseEntity<Message> addRole(WorkspaceRoleDTO workspaceRoleDTO);
}
