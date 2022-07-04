package uz.sudev.clickapp.service.implement;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.sudev.clickapp.entity.ProjectUser;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.ProjectUserDTO;

import java.util.UUID;

public interface ProjectUserImplement {
    ResponseEntity<Message> addProjectUser(ProjectUserDTO projectUserDTO);

    ResponseEntity<Message> editProjectUser(UUID id, ProjectUserDTO projectUserDTO);

    ResponseEntity<Message> deleteProjectUser(UUID id);

    ResponseEntity<Page<ProjectUser>> getUsersOfProject(Long projectId, int page, int size);

    ResponseEntity<ProjectUser> getUserOfProject(Long projectId, UUID userId);
}
