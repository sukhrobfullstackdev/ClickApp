package uz.sudev.clickapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.sudev.clickapp.entity.*;
import uz.sudev.clickapp.entity.enums.WorkspaceRoleName;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.WorkspaceDTO;
import uz.sudev.clickapp.repository.*;
import uz.sudev.clickapp.service.implement.WorkspaceServiceImplement;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkspaceService implements WorkspaceServiceImplement {
    final WorkspaceRepository workspaceRepository;
    final AttachmentRepository attachmentRepository;
    final UserRepository userRepository;
    final WorkspaceUserRepository workspaceUserRepository;
    final WorkspaceRoleRepository workspaceRoleRepository;

    public WorkspaceService(WorkspaceRepository workspaceRepository, AttachmentRepository attachmentRepository, UserRepository userRepository, WorkspaceUserRepository workspaceUserRepository, WorkspaceRoleRepository workspaceRoleRepository) {
        this.workspaceRepository = workspaceRepository;
        this.attachmentRepository = attachmentRepository;
        this.userRepository = userRepository;
        this.workspaceUserRepository = workspaceUserRepository;
        this.workspaceRoleRepository = workspaceRoleRepository;
    }

    @Override
    public ResponseEntity<Workspace> getWorkspace(Long id) {
        Optional<Workspace> optionalWorkspace = workspaceRepository.findById(id);
        return optionalWorkspace.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @Override
    public ResponseEntity<Page<Workspace>> getWorkspaces(int page, int size) {
        return ResponseEntity.ok(workspaceRepository.findAll(PageRequest.of(page, size)));
    }

    @Override
    public ResponseEntity<Message> addWorkspace(WorkspaceDTO workspaceDTO, User user) {
        if (!workspaceRepository.existsByOwnerIdAndName(user.getId(), workspaceDTO.getName())) {
            Workspace workspace = new Workspace();
            workspace.setName(workspaceDTO.getName());
            workspace.setColor(workspaceDTO.getColor());
            workspace.setOwner(user);
            if (workspaceDTO.getAvatarId() != null) {
                Optional<Attachment> optionalAttachment = attachmentRepository.findById(workspaceDTO.getAvatarId());
                if (optionalAttachment.isPresent()) {
                    workspace.setAvatar(optionalAttachment.get());
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "Your avatar is not found!"));
                }
            }
            Workspace savedWorkspace = workspaceRepository.save(workspace);
            WorkspaceRole workspaceRole = new WorkspaceRole(savedWorkspace, WorkspaceRoleName.ROLE_OWNER.name(), null);
            WorkspaceUser workspaceUser = new WorkspaceUser(savedWorkspace, user, workspaceRole, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
            workspaceUserRepository.save(workspaceUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "The workspace is successfully created!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The workspace is already exists!"));
        }
    }

    @Override
    public ResponseEntity<Message> editWorkspace(Long id, WorkspaceDTO workspaceDTO) {
        Optional<Workspace> optionalWorkspace = workspaceRepository.findById(id);
        if (optionalWorkspace.isPresent()) {
            Workspace workspace = optionalWorkspace.get();
            workspace.setName(workspace.getName());
            workspace.setColor(workspace.getColor());
            if (workspaceDTO.getAvatarId() != null) {
                Optional<Attachment> optionalAttachment = attachmentRepository.findById(workspaceDTO.getAvatarId());
                if (optionalAttachment.isPresent()) {
                    workspace.setAvatar(optionalAttachment.get());
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "Your avatar is not found!"));
                }
            }
            workspaceRepository.save(workspace);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "The workspace is successfully edited!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The workspace is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> changeOwner(Long id, UUID ownerId) {
        Optional<Workspace> optionalWorkspace = workspaceRepository.findById(id);
        Optional<User> optionalUser = userRepository.findById(ownerId);
        if (optionalWorkspace.isPresent()) {
            Workspace workspace = optionalWorkspace.get();
            if (optionalUser.isPresent()) {
                workspace.setOwner(optionalUser.get());
                workspaceRepository.save(workspace);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "The owner has been successfully changed!"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The user who you want to assign as an owner is not found!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The workspace is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> deleteWorkspace(Long id) {
        Optional<Workspace> optionalWorkspace = workspaceRepository.findById(id);
        if (optionalWorkspace.isPresent()) {
            workspaceRepository.delete(optionalWorkspace.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Message(true, "The workspace is successfully deleted!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The workspace is not found!"));
        }
    }
}
