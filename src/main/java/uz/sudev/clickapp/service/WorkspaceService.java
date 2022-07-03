package uz.sudev.clickapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.sudev.clickapp.entity.Attachment;
import uz.sudev.clickapp.entity.User;
import uz.sudev.clickapp.entity.Workspace;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.WorkspaceDTO;
import uz.sudev.clickapp.repository.AttachmentRepository;
import uz.sudev.clickapp.repository.WorkspaceRepository;
import uz.sudev.clickapp.service.implement.WorkspaceServiceImplement;

import java.util.Optional;
import java.util.UUID;

@Service
public class WorkspaceService implements WorkspaceServiceImplement {
    final WorkspaceRepository workspaceRepository;
    final AttachmentRepository attachmentRepository;

    public WorkspaceService(WorkspaceRepository workspaceRepository, AttachmentRepository attachmentRepository) {
        this.workspaceRepository = workspaceRepository;
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public ResponseEntity<Workspace> getWorkspace(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Page<Workspace>> getWorkspaces(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Message> addWorkspace(WorkspaceDTO workspaceDTO, User user) {
        if (!workspaceRepository.existsByOwnerIdAndName(user.getId(), workspaceDTO.getName())) {
            if (workspaceDTO.getAvatarId() != null) {
                Optional<Attachment> optionalAttachment = attachmentRepository.findById(workspaceDTO.getAvatarId());
                if (optionalAttachment.isPresent()) {
                    workspaceRepository.save(new Workspace(workspaceDTO.getName(), workspaceDTO.getColor(), user, optionalAttachment.get()));
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "Your avatar is not found!"));
                }
            } else {
                workspaceRepository.save(new Workspace(workspaceDTO.getName(), workspaceDTO.getColor(), user));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "The workspace is successfully created!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The workspace is already exists!"));
        }
    }

    @Override
    public ResponseEntity<Message> editWorkspace(Long id, WorkspaceDTO workspaceDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Message> changeOwner(Long id, UUID ownerId) {
        return null;
    }

    @Override
    public ResponseEntity<Message> deleteWorkspace(Long id) {
        return null;
    }
}
