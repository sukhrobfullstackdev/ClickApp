package uz.sudev.clickapp.service.implement;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.sudev.clickapp.entity.Project;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.ProjectDTO;

import java.util.UUID;

public interface ProjectImplement {
    ResponseEntity<Page<Project>> getProjects(int page, int size, UUID spaceId);

    ResponseEntity<Project> getProject(Long projectId, UUID spaceId);

    ResponseEntity<Message> addProject(ProjectDTO projectDTO);

    ResponseEntity<Message> editProject(Long id, ProjectDTO projectDTO);

    ResponseEntity<Message> deleteProject(Long id);
}
