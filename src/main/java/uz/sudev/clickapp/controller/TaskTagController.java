package uz.sudev.clickapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sudev.clickapp.entity.TaskTag;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.TaskTagDTO;
import uz.sudev.clickapp.service.implement.TaskTagService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/taskTag")
public class TaskTagController {
    final TaskTagService taskTagService;

    public TaskTagController(TaskTagService taskTagService) {
        this.taskTagService = taskTagService;
    }

    @GetMapping
    public ResponseEntity<Page<TaskTag>> getTaskTags(@RequestParam int page, @RequestParam int size) {
        return taskTagService.getTaskTags(page, size);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskTag> getTaskTag(@PathVariable UUID id) {
        return taskTagService.getTaskTag(id);
    }

    @PostMapping
    public ResponseEntity<Message> addTaskTag(@RequestBody TaskTagDTO taskTagDTO) {
        return taskTagService.addTaskTag(taskTagDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Message> editTaskTag(@RequestBody TaskTagDTO taskTagDTO, @PathVariable UUID id) {
        return taskTagService.editTaskTag(taskTagDTO, id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Message> deleteTaskTag(@PathVariable UUID id) {
        return taskTagService.deleteTaskTag(id);
    }
}
