package uz.sudev.clickapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.sudev.clickapp.entity.User;
import uz.sudev.clickapp.entity.Workspace;
import uz.sudev.clickapp.entity.WorkspacePermission;
import uz.sudev.clickapp.entity.WorkspaceRole;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.WorkspaceRoleDTO;
import uz.sudev.clickapp.repository.WorkspacePermissionRepository;
import uz.sudev.clickapp.repository.WorkspaceRepository;
import uz.sudev.clickapp.repository.WorkspaceRoleRepository;
import uz.sudev.clickapp.service.implement.WorkspaceRoleImplement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkspaceRoleService implements WorkspaceRoleImplement {
    final WorkspaceRepository workspaceRepository;
    final WorkspaceRoleRepository workspaceRoleRepository;
    final WorkspacePermissionRepository workspacePermissionRepository;

    public WorkspaceRoleService(WorkspaceRepository workspaceRepository, WorkspaceRoleRepository workspaceRoleRepository,WorkspacePermissionRepository workspacePermissionRepository) {
        this.workspaceRepository = workspaceRepository;
        this.workspaceRoleRepository = workspaceRoleRepository;
        this.workspacePermissionRepository = workspacePermissionRepository;
    }

    @Override
    public ResponseEntity<Page<WorkspaceRole>> getRoles(int page, int size, Long workspaceId) {
        return ResponseEntity.ok(workspaceRoleRepository.findAllByWorkspaceId(workspaceId, PageRequest.of(page, size)));
    }

    @Override
    public ResponseEntity<WorkspaceRole> getRole(Long workspaceId, UUID roleId) {
        Optional<WorkspaceRole> optionalWorkspaceRole = workspaceRoleRepository.findByIdAndWorkspaceId(roleId, workspaceId);
        return optionalWorkspaceRole.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @Override
    public ResponseEntity<Message> addRole(WorkspaceRoleDTO workspaceRoleDTO) {
        Optional<Workspace> optionalWorkspace = workspaceRepository.findById(workspaceRoleDTO.getWorkspaceId());
        if (optionalWorkspace.isPresent()) {
            if (!workspaceRoleRepository.existsByNameAndWorkspace(workspaceRoleDTO.getName(), optionalWorkspace.get())) {
                WorkspaceRole workspaceRole = workspaceRoleRepository.save(new WorkspaceRole(optionalWorkspace.get(), workspaceRoleDTO.getName(), workspaceRoleDTO.getExtendsRole()));
                List<WorkspacePermission> allPermissions = workspacePermissionRepository.findWorkspacePermissionsByWorkspaceRole_ExtendsRole(workspaceRoleDTO.getExtendsRole());
                List<WorkspacePermission> savingWorkspacePermission = new ArrayList<>();
                for (WorkspacePermission permission : allPermissions) {
                    savingWorkspacePermission.add(new WorkspacePermission(workspaceRole,permission.getPermission()));
                }
                workspacePermissionRepository.saveAll(savingWorkspacePermission);
                return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "The role has been successfully created!"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "This role is already exists in this workspace!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The workspace is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> editRole(UUID id, WorkspaceRoleDTO workspaceRoleDTO) {
        Optional<Workspace> optionalWorkspace = workspaceRepository.findById(workspaceRoleDTO.getWorkspaceId());
        if (optionalWorkspace.isPresent()) {
            Optional<WorkspaceRole> optionalWorkspaceRole = workspaceRoleRepository.findById(id);
            if (optionalWorkspaceRole.isPresent()) {
                if (!workspaceRoleRepository.existsByNameAndWorkspaceAndIdNot(workspaceRoleDTO.getName(), optionalWorkspace.get(), id)) {
                    WorkspaceRole workspaceRole = optionalWorkspaceRole.get();
                    workspaceRole.setName(workspaceRole.getName());
                    workspaceRole.setExtendsRole(workspaceRole.getExtendsRole());
                    workspaceRoleRepository.save(workspaceRole);
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "The workspace role is successfully edited!"));
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The workspace role is already exists!"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The workspace role is not found!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The workspace is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> deleteRole(UUID id) {
        Optional<WorkspaceRole> optionalWorkspaceRole = workspaceRoleRepository.findById(id);
        if (optionalWorkspaceRole.isPresent()) {
            workspaceRoleRepository.delete(optionalWorkspaceRole.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Message(true, "The workspace role is successfully deleted!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The workspace role is not found!"));
        }
    }
}
