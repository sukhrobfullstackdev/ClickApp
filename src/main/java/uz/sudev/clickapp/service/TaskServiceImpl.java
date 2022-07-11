package uz.sudev.clickapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.sudev.clickapp.entity.Category;
import uz.sudev.clickapp.entity.Priority;
import uz.sudev.clickapp.entity.Status;
import uz.sudev.clickapp.entity.Task;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.TaskDTO;
import uz.sudev.clickapp.repository.CategoryRepository;
import uz.sudev.clickapp.repository.PriorityRepository;
import uz.sudev.clickapp.repository.StatusRepository;
import uz.sudev.clickapp.repository.TaskRepository;
import uz.sudev.clickapp.service.implement.TaskService;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    final TaskRepository taskRepository;
    final StatusRepository statusRepository;
    final CategoryRepository categoryRepository;
    final PriorityRepository priorityRepository;

    public TaskServiceImpl(TaskRepository taskRepository, StatusRepository statusRepository, CategoryRepository categoryRepository, PriorityRepository priorityRepository) {
        this.taskRepository = taskRepository;
        this.statusRepository = statusRepository;
        this.categoryRepository = categoryRepository;
        this.priorityRepository = priorityRepository;
    }

    @Override
    public ResponseEntity<Page<Task>> getTasks(int page, int size) {
        return ResponseEntity.ok(taskRepository.findAll(PageRequest.of(page, size)));
    }

    @Override
    public ResponseEntity<Task> getTask(UUID id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @Override
    public ResponseEntity<Message> addTask(TaskDTO taskDTO) {
        Optional<Category> optionalCategory = categoryRepository.findById(taskDTO.getCategoryId());
        if (optionalCategory.isPresent()) {
            if (!taskRepository.existsByNameAndCategoryId(taskDTO.getName(), taskDTO.getCategoryId())) {
                Task task = new Task();
                task.setName(taskDTO.getName());
                task.setDescription(taskDTO.getDescription());
                task.setCategory(optionalCategory.get());
                if (taskDTO.getStatusId() != null) {
                    Optional<Status> optionalStatus = statusRepository.findById(taskDTO.getStatusId());
                    if (optionalStatus.isPresent()) {
                        task.setStatus(optionalStatus.get());
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The status is not found!"));
                    }
                }
                if (taskDTO.getPriorityId() != null) {
                    Optional<Priority> optionalPriority = priorityRepository.findById(taskDTO.getPriorityId());
                    if (optionalPriority.isPresent()) {
                        task.setPriority(optionalPriority.get());
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The priority is not found!"));
                    }
                }
                if (taskDTO.getParentTaskId() != null) {
                    Optional<Task> optionalTaskParent = taskRepository.findById(taskDTO.getParentTaskId());
                    if (optionalTaskParent.isPresent()) {
                        task.setParentTask(optionalTaskParent.get());
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The parent task is not found!"));
                    }
                }
                if (taskDTO.isStartTimeHas()) {
                    task.setStartTimeHas(taskDTO.isStartTimeHas());
                    task.setStartedDate(taskDTO.getStartedDate());
                } else {
                    task.setStartTimeHas(taskDTO.isStartTimeHas());
                }
                if (taskDTO.isDueTimeHas()) {
                    task.setDueTimeHas(taskDTO.isStartTimeHas());
                    task.setDueDate(taskDTO.getDueDate());
                } else {
                    task.setDueTimeHas(taskDTO.isStartTimeHas());
                }
                task.setActivatedDate(taskDTO.getActivatedDate());
                taskRepository.save(task);
                return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "The task has been successfully added!"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The task is already exists!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The category is already exists!"));
        }
    }

    @Override
    public ResponseEntity<Message> editTask(TaskDTO taskDTO, UUID id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            if (!taskRepository.existsByNameAndCategoryIdAndIdNot(taskDTO.getName(), taskDTO.getCategoryId(), id)) {
                Optional<Category> optionalCategory = categoryRepository.findById(taskDTO.getCategoryId());
                if (optionalCategory.isPresent()) {
                    Task task = optionalTask.get();
                    task.setName(taskDTO.getName());
                    task.setDescription(taskDTO.getDescription());
                    task.setCategory(optionalCategory.get());
                    if (taskDTO.getStatusId() != null) {
                        Optional<Status> optionalStatus = statusRepository.findById(taskDTO.getStatusId());
                        if (optionalStatus.isPresent()) {
                            task.setStatus(optionalStatus.get());
                        } else {
                            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The status is not found!"));
                        }
                    }
                    if (taskDTO.getPriorityId() != null) {
                        Optional<Priority> optionalPriority = priorityRepository.findById(taskDTO.getPriorityId());
                        if (optionalPriority.isPresent()) {
                            task.setPriority(optionalPriority.get());
                        } else {
                            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The priority is not found!"));
                        }
                    }
                    if (taskDTO.getParentTaskId() != null) {
                        Optional<Task> optionalTaskParent = taskRepository.findById(taskDTO.getParentTaskId());
                        if (optionalTaskParent.isPresent()) {
                            task.setParentTask(optionalTaskParent.get());
                        } else {
                            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The parent task is not found!"));
                        }
                    }
                    if (taskDTO.isStartTimeHas()) {
                        task.setStartTimeHas(taskDTO.isStartTimeHas());
                        task.setStartedDate(taskDTO.getStartedDate());
                    } else {
                        task.setStartTimeHas(taskDTO.isStartTimeHas());
                    }
                    if (taskDTO.isDueTimeHas()) {
                        task.setDueTimeHas(taskDTO.isStartTimeHas());
                        task.setDueDate(taskDTO.getDueDate());
                    } else {
                        task.setDueTimeHas(taskDTO.isStartTimeHas());
                    }
                    task.setActivatedDate(taskDTO.getActivatedDate());
                    taskRepository.save(task);
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "The task has been successfully edited!"));
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The category is not found!"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The task is already exists!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The task is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> deleteTask(UUID id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            taskRepository.delete(optionalTask.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Message(true, "The tas has been successfully deleted"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Override
    public ResponseEntity<Message> changeStatus(UUID taskId, UUID statusId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        Optional<Status> optionalStatus = statusRepository.findById(statusId);
        if (optionalTask.isPresent()) {
            if (optionalStatus.isPresent()) {
                Task task = optionalTask.get();
                task.setStatus(optionalStatus.get());
                taskRepository.save(task);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "The status of task has been successfully changed!"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The status is not found!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The task is not found!"));
        }
    }
}
