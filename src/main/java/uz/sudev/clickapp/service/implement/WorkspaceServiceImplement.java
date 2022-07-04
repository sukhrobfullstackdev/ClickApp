package uz.sudev.clickapp.service.implement;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.sudev.clickapp.entity.User;
import uz.sudev.clickapp.entity.Workspace;
import uz.sudev.clickapp.payload.MemberDTO;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.WorkspaceDTO;

import java.util.UUID;

public interface WorkspaceServiceImplement {
    ResponseEntity<Workspace> getWorkspace(Long id);

    ResponseEntity<Page<Workspace>> getWorkspaces(int page, int size);

    ResponseEntity<Message> addWorkspace(WorkspaceDTO workspaceDTO, User user);

    ResponseEntity<Message> editWorkspace(Long id, WorkspaceDTO workspaceDTO);

    ResponseEntity<Message> changeOwner(Long id, UUID ownerId);

    ResponseEntity<Message> deleteWorkspace(Long id);

    ResponseEntity<Message> addOrEditOrRemoveMemberOfWorkspace(Long workspaceId, MemberDTO memberDTO);
    ResponseEntity<Message> joinToWorkspace(Long workspaceId, User user);
}
