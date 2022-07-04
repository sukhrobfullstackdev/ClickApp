package uz.sudev.clickapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.sudev.clickapp.entity.*;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.SpaceDTO;
import uz.sudev.clickapp.repository.AttachmentRepository;
import uz.sudev.clickapp.repository.IconRepository;
import uz.sudev.clickapp.repository.SpaceRepository;
import uz.sudev.clickapp.repository.WorkspaceRepository;
import uz.sudev.clickapp.service.implement.SpaceImplement;

import java.util.Optional;
import java.util.UUID;

@Service
public class SpaceService implements SpaceImplement {
    final WorkspaceRepository workspaceRepository;
    final SpaceRepository spaceRepository;
    final AttachmentRepository attachmentRepository;
    final IconRepository iconRepository;

    public SpaceService(WorkspaceRepository workspaceRepository, SpaceRepository spaceRepository, AttachmentRepository attachmentRepository, IconRepository iconRepository) {
        this.workspaceRepository = workspaceRepository;
        this.spaceRepository = spaceRepository;
        this.attachmentRepository = attachmentRepository;
        this.iconRepository = iconRepository;
    }

    @Override
    public ResponseEntity<Message> addSpace(SpaceDTO spaceDTO, User user) {
        Optional<Workspace> optionalWorkspace = workspaceRepository.findById(spaceDTO.getWorkspaceId());
        if (optionalWorkspace.isPresent()) {
            if (!spaceRepository.existsByWorkspaceAndName(optionalWorkspace.get(), spaceDTO.getName())) {
                Space space = new Space();
                space.setName(spaceDTO.getName());
                space.setColor(spaceDTO.getColor());
                space.setOwner(user);
                space.setWorkspace(optionalWorkspace.get());
                space.setAccessType(spaceDTO.getAccessType());
                if (spaceDTO.getAvatarId() != null) {
                    Optional<Attachment> optionalAttachment = attachmentRepository.findById(spaceDTO.getAvatarId());
                    if (optionalAttachment.isPresent()) {
                        space.setAvatar(optionalAttachment.get());
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The attachment is not found!"));
                    }
                }
                if (spaceDTO.getIconId() != null) {
                    Optional<Icon> optionalIcon = iconRepository.findById(spaceDTO.getIconId());
                    if (optionalIcon.isPresent()) {
                        space.setIcon(optionalIcon.get());
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The icon is not found!"));
                    }
                }
                spaceRepository.save(space);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "The space is successfully added!"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The space is already exists by this name!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The workspace is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> editSpace(SpaceDTO spaceDTO, UUID id) {
        Optional<Space> optionalSpace = spaceRepository.findById(id);
        if (optionalSpace.isPresent()) {
            Optional<Workspace> optionalWorkspace = workspaceRepository.findById(spaceDTO.getWorkspaceId());
            if (optionalWorkspace.isPresent()) {
                if (!spaceRepository.existsByWorkspaceAndNameAndIdNot(optionalWorkspace.get(), spaceDTO.getName(), id)) {
                    Space space = optionalSpace.get();
                    space.setName(spaceDTO.getName());
                    space.setColor(spaceDTO.getColor());
                    space.setAccessType(spaceDTO.getAccessType());
                    if (spaceDTO.getAvatarId() != null) {
                        Optional<Attachment> optionalAttachment = attachmentRepository.findById(spaceDTO.getAvatarId());
                        if (optionalAttachment.isPresent()) {
                            space.setAvatar(optionalAttachment.get());
                        } else {
                            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The attachment is not found!"));
                        }
                    }
                    if (spaceDTO.getIconId() != null) {
                        Optional<Icon> optionalIcon = iconRepository.findById(spaceDTO.getIconId());
                        if (optionalIcon.isPresent()) {
                            space.setIcon(optionalIcon.get());
                        } else {
                            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The icon is not found!"));
                        }
                    }
                    spaceRepository.save(space);
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "The space is successfully added!"));
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The space is already exists in this workspace!"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The workspace is not found!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The space is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> deleteSpace(UUID id) {
        Optional<Space> optionalSpace = spaceRepository.findById(id);
        if (optionalSpace.isPresent()) {
            spaceRepository.delete(optionalSpace.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Message(true, "The space is successfully deleted!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The space is not found!"));
        }
    }

    @Override
    public ResponseEntity<Page<Space>> getSpaces(Long workspaceId, int page, int size) {
        return ResponseEntity.ok(spaceRepository.findAllByWorkspaceId(workspaceId, PageRequest.of(page, size)));
    }

    @Override
    public ResponseEntity<Space> getSpace(Long workspaceId, UUID id) {
        Optional<Space> optionalSpace = spaceRepository.findByIdAndWorkspaceId(id, workspaceId);
        return optionalSpace.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
