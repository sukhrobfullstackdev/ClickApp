package uz.sudev.clickapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.sudev.clickapp.entity.Project;
import uz.sudev.clickapp.entity.Space;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.ProjectDTO;
import uz.sudev.clickapp.repository.ProjectRepository;
import uz.sudev.clickapp.repository.SpaceRepository;
import uz.sudev.clickapp.service.implement.ProjectImplement;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService implements ProjectImplement {
    final ProjectRepository projectRepository;
    final SpaceRepository spaceRepository;

    public ProjectService(ProjectRepository projectRepository, SpaceRepository spaceRepository) {
        this.projectRepository = projectRepository;
        this.spaceRepository = spaceRepository;
    }

    @Override
    public ResponseEntity<Page<Project>> getProjects(int page, int size, UUID spaceId) {
        return ResponseEntity.ok(projectRepository.findAllBySpaceId(spaceId, PageRequest.of(page, size)));
    }

    @Override
    public ResponseEntity<Project> getProject(Long projectId, UUID spaceId) {
        Optional<Project> optionalProject = projectRepository.findBySpaceIdAndId(spaceId, projectId);
        return optionalProject.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @Override
    public ResponseEntity<Message> addProject(ProjectDTO projectDTO) {
        Optional<Space> optionalSpace = spaceRepository.findById(projectDTO.getSpaceId());
        if (optionalSpace.isPresent()) {
            if (!projectRepository.existsByNameAndSpaceId(projectDTO.getName(), projectDTO.getSpaceId())) {
                projectRepository.save(new Project(projectDTO.getName(), optionalSpace.get(), projectDTO.getAccessType(), projectDTO.isArchived(), projectDTO.getColor()));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The space has been successfully added!"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The project is already exists!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The space is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> editProject(Long id, ProjectDTO projectDTO) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            if (!projectRepository.existsByNameAndSpaceIdAndIdNot(projectDTO.getName(), projectDTO.getSpaceId(), id)) {
                Optional<Space> optionalSpace = spaceRepository.findById(projectDTO.getSpaceId());
                if (optionalSpace.isPresent()) {
                    Project project = optionalProject.get();
                    project.setName(projectDTO.getName());
                    project.setArchived(projectDTO.isArchived());
                    project.setAccessType(projectDTO.getAccessType());
                    project.setColor(projectDTO.getColor());
                    project.setSpace(optionalSpace.get());
                    projectRepository.save(project);
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "The project has been successfully edited!"));
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The space is not found!"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The project is already exists!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The project is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> deleteProject(Long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            projectRepository.delete(optionalProject.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Message(true, "The project has been successfully deleted!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The project is not found!"));
        }
    }
}
