package uz.sudev.clickapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.sudev.clickapp.entity.Project;
import uz.sudev.clickapp.entity.ProjectUser;
import uz.sudev.clickapp.entity.User;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.ProjectUserDTO;
import uz.sudev.clickapp.repository.ProjectRepository;
import uz.sudev.clickapp.repository.ProjectUserRepository;
import uz.sudev.clickapp.repository.UserRepository;
import uz.sudev.clickapp.service.implement.ProjectUserImplement;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectUserService implements ProjectUserImplement {
    final ProjectRepository projectRepository;
    final ProjectUserRepository projectUserRepository;
    final UserRepository userRepository;

    public ProjectUserService(ProjectUserRepository projectUserRepository, ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.projectUserRepository = projectUserRepository;
    }

    @Override
    public ResponseEntity<Page<ProjectUser>> getUsersOfProject(Long projectId, int page, int size) {
        return ResponseEntity.ok(projectUserRepository.findAllByProjectId(projectId, PageRequest.of(page, size)));
    }

    @Override
    public ResponseEntity<ProjectUser> getUserOfProject(Long projectId, UUID userId) {
        Optional<ProjectUser> optionalProjectUser = projectUserRepository.findByUserIdAndProjectId(userId, projectId);
        return optionalProjectUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @Override
    public ResponseEntity<Message> addProjectUser(ProjectUserDTO projectUserDTO) {
        Optional<Project> optionalProject = projectRepository.findById(projectUserDTO.getProjectId());
        if (optionalProject.isPresent()) {
            Optional<User> optionalUser = userRepository.findById(projectUserDTO.getUserId());
            if (optionalUser.isPresent()) {
                if (!projectUserRepository.existsByUserIdAndProjectId(projectUserDTO.getUserId(), projectUserDTO.getProjectId())) {
                    projectUserRepository.save(new ProjectUser(optionalProject.get(), optionalUser.get(), projectUserDTO.getTaskPermission()));
                    return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "The user has been successfully added to the project!"));
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The user is already exists in this project!"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The user is not found!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The project is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> editProjectUser(UUID id, ProjectUserDTO projectUserDTO) {
        Optional<ProjectUser> optionalProjectUser = projectUserRepository.findById(id);
        if (optionalProjectUser.isPresent()) {
            Optional<Project> optionalProject = projectRepository.findById(projectUserDTO.getProjectId());
            if (optionalProject.isPresent()) {
                Optional<User> optionalUser = userRepository.findById(projectUserDTO.getUserId());
                if (optionalUser.isPresent()) {
                    if (!projectUserRepository.existsByUserIdAndProjectIdAndIdNot(projectUserDTO.getUserId(), projectUserDTO.getProjectId(), id)) {
                        ProjectUser projectUser = optionalProjectUser.get();
                        projectUser.setProject(optionalProject.get());
                        projectUser.setUser(optionalUser.get());
                        projectUser.setTaskPermission(projectUserDTO.getTaskPermission());
                        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "The user has been successfully edited!"));
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The user is already exists in this project!"));
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The user is not found!"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The project is not found!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The user is not found in this project!"));
        }
    }

    @Override
    public ResponseEntity<Message> deleteProjectUser(UUID id) {
        Optional<ProjectUser> optionalProjectUser = projectUserRepository.findById(id);
        if (optionalProjectUser.isPresent()) {
            projectUserRepository.delete(optionalProjectUser.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Message(true, "The user has been successfully deleted by this project!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The user is not found in this project!"));
        }
    }
}
