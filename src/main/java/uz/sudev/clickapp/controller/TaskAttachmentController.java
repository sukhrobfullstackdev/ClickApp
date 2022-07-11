package uz.sudev.clickapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sudev.clickapp.entity.TaskAttachment;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.TaskAttachmentDTO;
import uz.sudev.clickapp.service.implement.TaskAttachmentService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/taskAttachment")
public class TaskAttachmentController {
    final TaskAttachmentService taskAttachmentService;

    public TaskAttachmentController(TaskAttachmentService taskAttachmentService) {
        this.taskAttachmentService = taskAttachmentService;
    }

    @GetMapping
    public ResponseEntity<Page<TaskAttachment>> getTaskAttachments(@RequestParam int page, @RequestParam int size) {
        return taskAttachmentService.getTaskAttachments(page, size);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskAttachment> getTaskAttachment(@PathVariable UUID id) {
        return taskAttachmentService.getTaskAttachment(id);
    }

    @PostMapping
    public ResponseEntity<Message> addTaskAttachment(@RequestBody TaskAttachmentDTO taskAttachmentDTO) {
        return taskAttachmentService.addTaskAttachment(taskAttachmentDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Message> editTaskAttachment(@PathVariable UUID id, @RequestBody TaskAttachmentDTO taskAttachmentDTO) {
        return taskAttachmentService.editTaskAttachment(taskAttachmentDTO, id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Message> deleteTaskAttachment(@PathVariable UUID id) {
        return taskAttachmentService.deleteTaskAttachment(id);
    }
}
