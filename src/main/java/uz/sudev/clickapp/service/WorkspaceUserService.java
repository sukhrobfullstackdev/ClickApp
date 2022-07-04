package uz.sudev.clickapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.sudev.clickapp.entity.WorkspaceUser;
import uz.sudev.clickapp.entity.enums.WorkspaceRoleName;
import uz.sudev.clickapp.repository.WorkspaceUserRepository;
import uz.sudev.clickapp.service.implement.WorkspaceUserImplement;

@Service
public class WorkspaceUserService implements WorkspaceUserImplement {
    final WorkspaceUserRepository workspaceUserRepository;

    public WorkspaceUserService(WorkspaceUserRepository workspaceUserRepository) {
        this.workspaceUserRepository = workspaceUserRepository;
    }

    @Override
    public ResponseEntity<Page<WorkspaceUser>> getWorkspaceGuests(int page, int size, Long workspaceId) {
        return ResponseEntity.ok(workspaceUserRepository.findAllByWorkspaceIdAndWorkspaceRole_Name(workspaceId, WorkspaceRoleName.ROLE_GUEST.name(), PageRequest.of(page, size)));
    }

    @Override
    public ResponseEntity<Page<WorkspaceUser>> getWorkspaceMembers(int page, int size, Long workspaceId) {
        return ResponseEntity.ok(workspaceUserRepository.findAllByWorkspaceIdAndWorkspaceRole_Name(workspaceId, WorkspaceRoleName.ROLE_MEMBER.name(), PageRequest.of(page, size)));
    }
}
