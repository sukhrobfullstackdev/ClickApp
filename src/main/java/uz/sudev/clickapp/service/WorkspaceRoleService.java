package uz.sudev.clickapp.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.sudev.clickapp.entity.Workspace;
import uz.sudev.clickapp.entity.WorkspaceRole;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.WorkspaceRoleDTO;
import uz.sudev.clickapp.repository.WorkspaceRepository;
import uz.sudev.clickapp.repository.WorkspaceRoleRepository;
import uz.sudev.clickapp.service.implement.WorkspaceRoleImplement;

import java.util.Optional;

@Service
public class WorkspaceRoleService implements WorkspaceRoleImplement {
    final WorkspaceRepository workspaceRepository;
    final WorkspaceRoleRepository workspaceRoleRepository;

    public WorkspaceRoleService(WorkspaceRepository workspaceRepository, WorkspaceRoleRepository workspaceRoleRepository) {
        this.workspaceRepository = workspaceRepository;
        this.workspaceRoleRepository = workspaceRoleRepository;
    }

    @Override
    public ResponseEntity<Message> addRole(WorkspaceRoleDTO workspaceRoleDTO) {
        Optional<Workspace> optionalWorkspace = workspaceRepository.findById(workspaceRoleDTO.getWorkspaceId());
        if (optionalWorkspace.isPresent()) {
            if (!workspaceRoleRepository.existsByNameAndWorkspace(workspaceRoleDTO.getName(), optionalWorkspace.get())) {
                workspaceRoleRepository.save(new WorkspaceRole(optionalWorkspace.get(), workspaceRoleDTO.getName(), workspaceRoleDTO.getExtendsRole()));
                return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "The role has been successfully created!"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "This role is already exists in this workspace!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The workspace is not found!"));
        }
    }
}
