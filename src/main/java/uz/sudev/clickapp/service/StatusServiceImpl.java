package uz.sudev.clickapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.sudev.clickapp.entity.Category;
import uz.sudev.clickapp.entity.Project;
import uz.sudev.clickapp.entity.Space;
import uz.sudev.clickapp.entity.Status;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.StatusDTO;
import uz.sudev.clickapp.repository.CategoryRepository;
import uz.sudev.clickapp.repository.ProjectRepository;
import uz.sudev.clickapp.repository.SpaceRepository;
import uz.sudev.clickapp.repository.StatusRepository;
import uz.sudev.clickapp.service.implement.StatusService;

import java.util.Optional;
import java.util.UUID;

@Service
public class StatusServiceImpl implements StatusService {
    final StatusRepository statusRepository;
    final ProjectRepository projectRepository;
    final SpaceRepository spaceRepository;
    final CategoryRepository categoryRepository;

    public StatusServiceImpl(StatusRepository statusRepository, ProjectRepository projectRepository, SpaceRepository spaceRepository, CategoryRepository categoryRepository) {
        this.statusRepository = statusRepository;
        this.projectRepository = projectRepository;
        this.spaceRepository = spaceRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity<Page<Status>> getStatuses(int page, int size) {
        return ResponseEntity.ok(statusRepository.findAll(PageRequest.of(page, size)));
    }

    @Override
    public ResponseEntity<Status> getStatus(UUID id) {
        Optional<Status> optionalStatus = statusRepository.findById(id);
        return optionalStatus.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @Override
    public ResponseEntity<Message> addStatus(StatusDTO statusDTO) {
        if (!statusRepository.existsByNameAndSpaceIdAndProjectId(statusDTO.getName(), statusDTO.getSpaceId(), statusDTO.getProjectId())) {
            Status status = new Status();
            status.setName(statusDTO.getName());
            status.setColor(statusDTO.getColor());
            status.setType(statusDTO.getType());
            if (statusDTO.getSpaceId() != null) {
                Optional<Category> optionalCategory = categoryRepository.findById(statusDTO.getCategoryId());
                if (optionalCategory.isPresent()) {
                    status.setCategory(optionalCategory.get());
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The category is not found!"));
                }
            }
            if (statusDTO.getProjectId() != null) {
                Optional<Project> optionalProject = projectRepository.findById(statusDTO.getProjectId());
                if (optionalProject.isPresent()) {
                    status.setProject(optionalProject.get());
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The project is not found!"));
                }
            }
            if (statusDTO.getSpaceId() != null) {
                Optional<Space> optionalSpace = spaceRepository.findById(statusDTO.getSpaceId());
                if (optionalSpace.isPresent()) {
                    status.setSpace(optionalSpace.get());
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The space is not found!"));
                }
            }
            statusRepository.save(status);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "The status has been successfully created!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The status is already exists!"));
        }
    }

    @Override
    public ResponseEntity<Message> editStatus(StatusDTO statusDTO, UUID id) {
        Optional<Status> optionalStatus = statusRepository.findById(id);
        if (optionalStatus.isPresent()) {
            if (!statusRepository.existsByNameAndSpaceIdAndProjectIdAndIdNot(statusDTO.getName(), statusDTO.getSpaceId(), statusDTO.getProjectId(), id)) {
                Status status = optionalStatus.get();
                status.setName(status.getName());
                status.setColor(status.getColor());
                status.setType(status.getType());
                if (statusDTO.getSpaceId() != null) {
                    Optional<Category> optionalCategory = categoryRepository.findById(statusDTO.getCategoryId());
                    if (optionalCategory.isPresent()) {
                        status.setCategory(optionalCategory.get());
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The category is not found!"));
                    }
                }
                if (statusDTO.getProjectId() != null) {
                    Optional<Project> optionalProject = projectRepository.findById(statusDTO.getProjectId());
                    if (optionalProject.isPresent()) {
                        status.setProject(optionalProject.get());
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The project is not found!"));
                    }
                }
                if (statusDTO.getSpaceId() != null) {
                    Optional<Space> optionalSpace = spaceRepository.findById(statusDTO.getSpaceId());
                    if (optionalSpace.isPresent()) {
                        status.setSpace(optionalSpace.get());
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The space is not found!"));
                    }
                }
                statusRepository.save(status);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "The status has been successfully edited!"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The status is already exists!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The space is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> deleteStatus(UUID id) {
        Optional<Status> optionalStatus = statusRepository.findById(id);
        if (optionalStatus.isPresent()) {
            statusRepository.delete(optionalStatus.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Message(true, "The status has been successfully deleted!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The status is not found!"));
        }
    }
}
