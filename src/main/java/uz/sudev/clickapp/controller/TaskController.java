package uz.sudev.clickapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sudev.clickapp.entity.Task;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.TaskDTO;
import uz.sudev.clickapp.service.implement.TaskService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/task")
public class TaskController {
    final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<Page<Task>> getTasks(@RequestParam int page, @RequestParam int size) {
        return taskService.getTasks(page, size);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Task> getTask(@PathVariable UUID id) {
        return taskService.getTask(id);
    }

    @PostMapping
    public ResponseEntity<Message> addTask(@RequestBody TaskDTO taskDTO) {
        return taskService.addTask(taskDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Message> editTask(@RequestBody TaskDTO taskDTO, @PathVariable UUID id) {
        return taskService.editTask(taskDTO, id);
    }
    @PutMapping(value = "/changeStatus/{taskId}/{statusId}")
    public ResponseEntity<Message> changeStatus(@PathVariable UUID taskId,@PathVariable UUID statusId) {
        return taskService.changeStatus(taskId, statusId);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Message> deleteTask(@PathVariable UUID id) {
        return taskService.deleteTask(id);
    }
}
