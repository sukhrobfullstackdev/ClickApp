package uz.sudev.clickapp.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.sudev.clickapp.entity.WorkspacePermission;
import uz.sudev.clickapp.entity.WorkspaceRole;
import uz.sudev.clickapp.entity.enums.WorkspacePermissionName;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.WorkspacePermissionDTO;
import uz.sudev.clickapp.repository.WorkspacePermissionRepository;
import uz.sudev.clickapp.repository.WorkspaceRoleRepository;
import uz.sudev.clickapp.service.implement.WorkspacePermissionImplement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkspacePermissionService implements WorkspacePermissionImplement {
    final WorkspacePermissionRepository workspacePermissionRepository;
    final WorkspaceRoleRepository workspaceRoleRepository;

    public WorkspacePermissionService(WorkspacePermissionRepository workspacePermissionRepository, WorkspaceRoleRepository workspaceRoleRepository) {
        this.workspacePermissionRepository = workspacePermissionRepository;
        this.workspaceRoleRepository = workspaceRoleRepository;
    }

    @Override
    public ResponseEntity<List<WorkspacePermission>> getAllPermissions(UUID workspaceRoleId) {
        return ResponseEntity.ok(workspacePermissionRepository.findAllByWorkspaceRoleId(workspaceRoleId));
    }

    @Override
    public ResponseEntity<Message> addPermissionsToRole(UUID workspaceRoleId, WorkspacePermissionDTO workspacePermissionDTO) {
        Optional<WorkspaceRole> optionalWorkspaceRole = workspaceRoleRepository.findById(workspaceRoleId);
        if (optionalWorkspaceRole.isPresent()) {
            WorkspaceRole workspaceRole = optionalWorkspaceRole.get();
            List<WorkspacePermissionName> allByWorkspaceRoleId = workspacePermissionRepository.findAllByWorkspaceRole(optionalWorkspaceRole.get());
            allByWorkspaceRoleId.addAll(workspacePermissionDTO.getPermissions());
            List<WorkspacePermission> workspacePermissions = new ArrayList<>();
            for (WorkspacePermissionName permission : allByWorkspaceRoleId) {
                workspacePermissions.add(new WorkspacePermission(workspaceRole, permission));
            }
            workspacePermissionRepository.saveAll(workspacePermissions);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "The permissions were assigned to this role!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The workspace role is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> editPermissionsToRole(UUID workspaceRoleId, WorkspacePermissionDTO workspacePermissionDTO) {
        Optional<WorkspaceRole> optionalWorkspaceRole = workspaceRoleRepository.findById(workspaceRoleId);
        if (optionalWorkspaceRole.isPresent()) {
            WorkspaceRole workspaceRole = optionalWorkspaceRole.get();
            List<WorkspacePermission> workspacePermissions = new ArrayList<>();
            for (WorkspacePermissionName permission : workspacePermissionDTO.getPermissions()) {
                workspacePermissions.add(new WorkspacePermission(workspaceRole, permission));
            }
            workspacePermissionRepository.saveAll(workspacePermissions);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "The permissions were assigned to this role!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The workspace role is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> deletePermissionsToRole(UUID workspaceRoleId) {
        workspacePermissionRepository.deleteAllByWorkspaceRoleId(workspaceRoleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Message(true, "The workspace role is successfully deleted!"));
    }
}
