package uz.sudev.clickapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.sudev.clickapp.entity.Task;
import uz.sudev.clickapp.entity.TaskUser;
import uz.sudev.clickapp.entity.User;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.TaskUserDTO;
import uz.sudev.clickapp.repository.TaskRepository;
import uz.sudev.clickapp.repository.TaskUserRepository;
import uz.sudev.clickapp.repository.UserRepository;
import uz.sudev.clickapp.service.implement.TaskUserService;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskUserServiceImpl implements TaskUserService {
    final TaskUserRepository taskUserRepository;
    final TaskRepository taskRepository;
    final UserRepository userRepository;

    public TaskUserServiceImpl(TaskUserRepository taskUserRepository, TaskRepository taskRepository, UserRepository userRepository) {
        this.taskUserRepository = taskUserRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<Page<TaskUser>> getTaskUsers(int page, int size) {
        return ResponseEntity.ok(taskUserRepository.findAll(PageRequest.of(page, size)));
    }

    @Override
    public ResponseEntity<TaskUser> getTaskUser(UUID id) {
        Optional<TaskUser> optionalTaskUser = taskUserRepository.findById(id);
        return optionalTaskUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @Override
    public ResponseEntity<Message> addTaskUser(TaskUserDTO taskUserDTO) {
        Optional<Task> optionalTask = taskRepository.findById(taskUserDTO.getTaskId());
        Optional<User> optionalUser = userRepository.findById(taskUserDTO.getUserId());
        if (optionalTask.isPresent()) {
            if (optionalUser.isPresent()) {
                if (!taskUserRepository.existsByTaskIdAndUserId(taskUserDTO.getTaskId(), taskUserDTO.getUserId())) {
                    taskUserRepository.save(new TaskUser(optionalTask.get(), optionalUser.get()));
                    return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "The user has been successfully assigned to this task!"));
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The user has been already assigned to this task!"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The user is not found!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "Tha task is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> editTaskUser(TaskUserDTO taskUserDTO, UUID id) {
        Optional<TaskUser> optionalTaskUser = taskUserRepository.findById(id);
        if (optionalTaskUser.isPresent()) {
            Optional<Task> optionalTask = taskRepository.findById(taskUserDTO.getTaskId());
            Optional<User> optionalUser = userRepository.findById(taskUserDTO.getUserId());
            if (optionalTask.isPresent()) {
                if (optionalUser.isPresent()) {
                    if (!taskUserRepository.existsByTaskIdAndUserIdAndIdNot(taskUserDTO.getTaskId(), taskUserDTO.getUserId(), id)) {
                        TaskUser taskUser = optionalTaskUser.get();
                        taskUser.setTask(optionalTask.get());
                        taskUser.setUser(optionalUser.get());
                        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "The task user has been successfully edited!"));
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The user has been already assigned to this task!"));
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The user is not found!"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "Tha task is not found!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The task user is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> deleteTaskUser(UUID id) {
        Optional<TaskUser> optionalTaskUser = taskUserRepository.findById(id);
        if (optionalTaskUser.isPresent()) {
            taskUserRepository.delete(optionalTaskUser.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Message(true, "The task user has been successfully deleted!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The task user is not found!"));
        }
    }
}
