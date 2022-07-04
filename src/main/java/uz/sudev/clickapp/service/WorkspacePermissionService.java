package uz.sudev.clickapp.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.sudev.clickapp.entity.WorkspacePermission;
import uz.sudev.clickapp.repository.WorkspacePermissionRepository;
import uz.sudev.clickapp.service.implement.WorkspacePermissionImplement;

import java.util.List;
import java.util.UUID;

@Service
public class WorkspacePermissionService implements WorkspacePermissionImplement {
    final WorkspacePermissionRepository workspacePermissionRepository;

    public WorkspacePermissionService(WorkspacePermissionRepository workspacePermissionRepository) {
        this.workspacePermissionRepository = workspacePermissionRepository;
    }

    @Override
    public ResponseEntity<List<WorkspacePermission>> getAllPermissions(UUID workspaceId) {
        return ResponseEntity.ok(workspacePermissionRepository.findAllByWorkspaceRoleId(workspaceId));
    }
}
