package uz.sudev.clickapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.sudev.clickapp.entity.Attachment;
import uz.sudev.clickapp.entity.Task;
import uz.sudev.clickapp.entity.TaskAttachment;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.TaskAttachmentDTO;
import uz.sudev.clickapp.repository.AttachmentRepository;
import uz.sudev.clickapp.repository.TaskAttachmentRepository;
import uz.sudev.clickapp.repository.TaskRepository;
import uz.sudev.clickapp.service.implement.TaskAttachmentService;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskAttachmentServiceImpl implements TaskAttachmentService {
    final TaskAttachmentRepository taskAttachmentRepository;
    final TaskRepository taskRepository;
    final AttachmentRepository attachmentRepository;

    public TaskAttachmentServiceImpl(TaskAttachmentRepository taskAttachmentRepository, TaskRepository taskRepository, AttachmentRepository attachmentRepository) {
        this.taskAttachmentRepository = taskAttachmentRepository;
        this.taskRepository = taskRepository;
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public ResponseEntity<Page<TaskAttachment>> getTaskAttachments(int page, int size) {
        return ResponseEntity.ok(taskAttachmentRepository.findAll(PageRequest.of(page, size)));
    }

    @Override
    public ResponseEntity<TaskAttachment> getTaskAttachment(UUID id) {
        Optional<TaskAttachment> optionalTaskAttachment = taskAttachmentRepository.findById(id);
        return optionalTaskAttachment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @Override
    public ResponseEntity<Message> addTaskAttachment(TaskAttachmentDTO taskAttachmentDTO) {
        Optional<Task> optionalTask = taskRepository.findById(taskAttachmentDTO.getTaskId());
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(taskAttachmentDTO.getAttachmentId());
        if (optionalTask.isPresent()) {
            if (optionalAttachment.isPresent()) {
                if (!taskAttachmentRepository.existsByAttachmentIdAndTaskId(taskAttachmentDTO.getAttachmentId(), taskAttachmentDTO.getTaskId())) {
                    taskAttachmentRepository.save(new TaskAttachment(optionalTask.get(), optionalAttachment.get(), taskAttachmentDTO.isPinCoverImage()));
                    return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "The task attachment has been successfully saved!"));
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The task attachment is already exists!"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The attachment is not found!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The task is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> editTaskAttachment(TaskAttachmentDTO taskAttachmentDTO, UUID id) {
        Optional<TaskAttachment> optionalTaskAttachment = taskAttachmentRepository.findById(id);
        if (optionalTaskAttachment.isPresent()) {
            TaskAttachment taskAttachment = optionalTaskAttachment.get();
            Optional<Task> optionalTask = taskRepository.findById(taskAttachmentDTO.getTaskId());
            Optional<Attachment> optionalAttachment = attachmentRepository.findById(taskAttachmentDTO.getAttachmentId());
            if (optionalTask.isPresent()) {
                if (optionalAttachment.isPresent()) {
                    if (!taskAttachmentRepository.existsByAttachmentIdAndTaskIdAndIdNot(taskAttachmentDTO.getAttachmentId(), taskAttachmentDTO.getTaskId(), id)) {
                        taskAttachment.setAttachment(optionalAttachment.get());
                        taskAttachment.setTask(optionalTask.get());
                        taskAttachment.setPinCoverImage(taskAttachment.isPinCoverImage());
                        taskAttachmentRepository.save(taskAttachment);
                        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "The task attachment has been successfully edited!"));
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The task attachment is already exists!"));
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The attachment is not found!"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The task is not found!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The task attachment is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> deleteTaskAttachment(UUID id) {
        Optional<TaskAttachment> optionalTaskAttachment = taskAttachmentRepository.findById(id);
        if (optionalTaskAttachment.isPresent()) {
            taskAttachmentRepository.save(optionalTaskAttachment.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Message(true, "The task attachment has been successfully deleted!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The task attachment is not found!"));
        }
    }
}
