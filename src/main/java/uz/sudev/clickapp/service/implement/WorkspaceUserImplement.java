package uz.sudev.clickapp.service.implement;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.sudev.clickapp.entity.WorkspaceUser;

public interface WorkspaceUserImplement {
    ResponseEntity<Page<WorkspaceUser>> getWorkspaceGuests(int page, int size, Long workspaceId);
    ResponseEntity<Page<WorkspaceUser>> getWorkspaceMembers(int page, int size, Long workspaceId);
}
