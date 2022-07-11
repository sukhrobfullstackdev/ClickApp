package uz.sudev.clickapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.sudev.clickapp.entity.Tag;
import uz.sudev.clickapp.entity.Task;
import uz.sudev.clickapp.entity.TaskTag;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.TaskTagDTO;
import uz.sudev.clickapp.repository.TagRepository;
import uz.sudev.clickapp.repository.TaskRepository;
import uz.sudev.clickapp.repository.TaskTagRepository;
import uz.sudev.clickapp.service.implement.TaskTagService;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskTagServiceImpl implements TaskTagService {
    final TaskTagRepository taskTagRepository;
    final TaskRepository taskRepository;
    final TagRepository tagRepository;

    public TaskTagServiceImpl(TaskTagRepository taskTagRepository, TaskRepository taskRepository, TagRepository tagRepository) {
        this.taskTagRepository = taskTagRepository;
        this.taskRepository = taskRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public ResponseEntity<Page<TaskTag>> getTaskTags(int page, int size) {
        return ResponseEntity.ok(taskTagRepository.findAll(PageRequest.of(page, size)));
    }

    @Override
    public ResponseEntity<TaskTag> getTaskTag(UUID id) {
        Optional<TaskTag> optionalTaskTag = taskTagRepository.findById(id);
        return optionalTaskTag.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @Override
    public ResponseEntity<Message> addTaskTag(TaskTagDTO taskTagDTO) {
        Optional<Tag> optionalTag = tagRepository.findById(taskTagDTO.getTagId());
        Optional<Task> optionalTask = taskRepository.findById(taskTagDTO.getTaskId());
        if (optionalTask.isPresent()) {
            if (optionalTag.isPresent()) {
                if (!taskTagRepository.existsByTaskIdAndTagId(taskTagDTO.getTaskId(), taskTagDTO.getTagId())) {
                    taskTagRepository.save(new TaskTag(optionalTask.get(), optionalTag.get()));
                    return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "The task tag has been successfully created!"));
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The task has already had this tag!"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The tag is not found!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The task is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> editTaskTag(TaskTagDTO taskTagDTO, UUID id) {
        Optional<TaskTag> optionalTaskTag = taskTagRepository.findById(id);
        if (optionalTaskTag.isPresent()) {
            Optional<Tag> optionalTag = tagRepository.findById(taskTagDTO.getTagId());
            Optional<Task> optionalTask = taskRepository.findById(taskTagDTO.getTaskId());
            if (optionalTask.isPresent()) {
                if (optionalTag.isPresent()) {
                    if (!taskTagRepository.existsByTaskIdAndTagIdAndIdNot(taskTagDTO.getTaskId(), taskTagDTO.getTagId(), id)) {
                        TaskTag taskTag = optionalTaskTag.get();
                        taskTag.setTag(optionalTag.get());
                        taskTag.setTask(optionalTask.get());
                        taskTagRepository.save(taskTag);
                        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "The task tag has been successfully edited!"));
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The task has already had this tag!"));
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The tag is not found!"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The task is not found!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The task tag is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> deleteTaskTag(UUID id) {
        Optional<TaskTag> optionalTaskTag = taskTagRepository.findById(id);
        if (optionalTaskTag.isPresent()) {
            taskTagRepository.delete(optionalTaskTag.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Message(true, "The task tag has been successfully deleted!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The task tag is not found!"));
        }
    }
}
