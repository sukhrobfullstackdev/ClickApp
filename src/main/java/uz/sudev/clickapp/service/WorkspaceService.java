package uz.sudev.clickapp.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.sudev.clickapp.entity.*;
import uz.sudev.clickapp.entity.enums.AddEditRemove;
import uz.sudev.clickapp.entity.enums.WorkspacePermissionName;
import uz.sudev.clickapp.entity.enums.WorkspaceRoleName;
import uz.sudev.clickapp.payload.MemberDTO;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.WorkspaceDTO;
import uz.sudev.clickapp.repository.*;
import uz.sudev.clickapp.service.implement.WorkspaceServiceImplement;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkspaceService implements WorkspaceServiceImplement {
    final WorkspaceRepository workspaceRepository;
    final AttachmentRepository attachmentRepository;
    final UserRepository userRepository;
    final WorkspaceUserRepository workspaceUserRepository;
    final WorkspaceRoleRepository workspaceRoleRepository;
    final WorkspacePermissionRepository workspacePermissionRepository;

    public WorkspaceService(WorkspaceRepository workspaceRepository, AttachmentRepository attachmentRepository, UserRepository userRepository, WorkspaceUserRepository workspaceUserRepository, WorkspaceRoleRepository workspaceRoleRepository, WorkspacePermissionRepository workspacePermissionRepository) {
        this.workspaceRepository = workspaceRepository;
        this.attachmentRepository = attachmentRepository;
        this.userRepository = userRepository;
        this.workspaceUserRepository = workspaceUserRepository;
        this.workspaceRoleRepository = workspaceRoleRepository;
        this.workspacePermissionRepository = workspacePermissionRepository;
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
            WorkspaceRole workspaceRole = workspaceRoleRepository.save(new WorkspaceRole(savedWorkspace, WorkspaceRoleName.ROLE_OWNER.name(), null));
            WorkspaceRole admin = workspaceRoleRepository.save(new WorkspaceRole(savedWorkspace, WorkspaceRoleName.ROLE_ADMIN.name(), null));
            WorkspaceRole member = workspaceRoleRepository.save(new WorkspaceRole(savedWorkspace, WorkspaceRoleName.ROLE_MEMBER.name(), null));
            WorkspaceRole guest = workspaceRoleRepository.save(new WorkspaceRole(savedWorkspace, WorkspaceRoleName.ROLE_GUEST.name(), null));
            List<WorkspacePermission> workspacePermissions = new ArrayList<>();
            for (WorkspacePermissionName permission : WorkspacePermissionName.values()) {
                workspacePermissions.add(new WorkspacePermission(workspaceRole, permission));
                if (permission.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_ADMIN)) {
                    workspacePermissions.add(new WorkspacePermission(admin, permission));
                }
                if (permission.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_MEMBER)) {
                    workspacePermissions.add(new WorkspacePermission(member, permission));
                }
                if (permission.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_GUEST)) {
                    workspacePermissions.add(new WorkspacePermission(guest, permission));
                }
            }
            workspacePermissionRepository.saveAll(workspacePermissions);
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

    @Override
    public ResponseEntity<Message> addOrEditOrRemoveMemberOfWorkspace(Long workspaceId, MemberDTO memberDTO) {
        if (memberDTO.getAddEditRemove().equals(AddEditRemove.ADD)) {
            Optional<Workspace> optionalWorkspace = workspaceRepository.findById(workspaceId);
            if (optionalWorkspace.isPresent()) {
                Optional<User> optionalUser = userRepository.findById(memberDTO.getMemberId());
                if (optionalUser.isPresent()) {
                    Optional<WorkspaceRole> optionalWorkspaceRole = workspaceRoleRepository.findById(memberDTO.getRoleId());
                    if (optionalWorkspaceRole.isPresent()) {
                        workspaceUserRepository.save(new WorkspaceUser(optionalWorkspace.get(), optionalUser.get(), optionalWorkspaceRole.get(), new Timestamp(System.currentTimeMillis())));
                        return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "The workspace user is successfully added!"));
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The workspace role is not found!"));
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The user is not found!"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The workspace is not found!"));
            }
        } else if (memberDTO.getAddEditRemove().equals(AddEditRemove.EDIT)) {
            Optional<WorkspaceUser> optionalWorkspaceUser = workspaceUserRepository.findByUserIdAndWorkspaceId(memberDTO.getMemberId(), workspaceId);
            Optional<WorkspaceRole> optionalWorkspaceRole = workspaceRoleRepository.findById(memberDTO.getRoleId());
            if (optionalWorkspaceUser.isPresent()) {
                if (optionalWorkspaceRole.isPresent()) {
                    WorkspaceUser workspaceUser = optionalWorkspaceUser.get();
                    workspaceUser.setWorkspaceRole(optionalWorkspaceRole.get());
                    workspaceUserRepository.save(workspaceUser);
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(false, "The workspace has been successfully edited!"));
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The workspace role is not found!"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The workspace us er is not found!"));
            }
        } else if (memberDTO.getAddEditRemove().equals(AddEditRemove.REMOVE)) {
            workspaceUserRepository.deleteByUserIdAndWorkspaceId(memberDTO.getMemberId(), workspaceId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Message(true, "The workspace is successfully deleted!"));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Message(false, "The method type is undefined!"));
        }
    }

    @Override
    public ResponseEntity<Message> joinToWorkspace(Long workspaceId, User user) {
        Optional<WorkspaceUser> optionalWorkspaceUser = workspaceUserRepository.findByUserIdAndWorkspaceId(user.getId(), workspaceId);
        if (optionalWorkspaceUser.isPresent()) {
            WorkspaceUser workspaceUser = optionalWorkspaceUser.get();
            workspaceUser.setDateJoined(new Timestamp(System.currentTimeMillis()));
            workspaceUserRepository.save(workspaceUser);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "You have successfully been added to the workspace!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The workspace user is not found!"));
        }
    }

    @Override
    public ResponseEntity<Page<Workspace>> getMyWorkspaces(int page, int size, User user) {
        return ResponseEntity.ok(workspaceRepository.findAllByOwner(user, PageRequest.of(page, size)));
    }
}
