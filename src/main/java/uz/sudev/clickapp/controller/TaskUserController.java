package uz.sudev.clickapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sudev.clickapp.entity.TaskUser;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.TaskUserDTO;
import uz.sudev.clickapp.service.implement.TaskUserService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/taskUser")
public class TaskUserController {
    final TaskUserService taskUserService;

    public TaskUserController(TaskUserService taskUserService) {
        this.taskUserService = taskUserService;
    }

    @GetMapping
    public ResponseEntity<Page<TaskUser>> getTaskUsers(@RequestParam int page, @RequestParam int size) {
        return taskUserService.getTaskUsers(page, size);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskUser> getTaskUser(@PathVariable UUID id) {
        return taskUserService.getTaskUser(id);
    }

    @PostMapping
    public ResponseEntity<Message> addTaskUser(@RequestBody TaskUserDTO taskUserDTO) {
        return taskUserService.addTaskUser(taskUserDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Message> editTaskUser(@RequestBody TaskUserDTO taskUserDTO, @PathVariable UUID id) {
        return taskUserService.editTaskUser(taskUserDTO, id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Message> deleteTaskUser(@PathVariable UUID id) {
        return taskUserService.deleteTaskUser(id);
    }
}
