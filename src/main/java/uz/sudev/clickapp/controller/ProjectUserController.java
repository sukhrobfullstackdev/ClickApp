package uz.sudev.clickapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sudev.clickapp.entity.ProjectUser;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.ProjectUserDTO;
import uz.sudev.clickapp.service.ProjectUserService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/projectUser")
public class ProjectUserController {
    final ProjectUserService projectUserService;

    public ProjectUserController(ProjectUserService projectUserService) {
        this.projectUserService = projectUserService;
    }

    @GetMapping(value = "/{projectId}")
    public ResponseEntity<Page<ProjectUser>> getUsersOfProject(@PathVariable Long projectId, @RequestParam int page, @RequestParam int size) {
        return projectUserService.getUsersOfProject(projectId, page, size);
    }
    @GetMapping(value = "/{projectId}/{userId}")
    public ResponseEntity<ProjectUser> getUserOfProject(@PathVariable Long projectId, @PathVariable UUID userId) {
        return projectUserService.getUserOfProject(projectId, userId);
    }

    @PostMapping
    public ResponseEntity<Message> addProjectUser(@RequestBody ProjectUserDTO projectUserDTO) {
        return projectUserService.addProjectUser(projectUserDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Message> editProjectUser(@PathVariable UUID id, @RequestBody ProjectUserDTO projectUserDTO) {
        return projectUserService.editProjectUser(id, projectUserDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Message> deleteProjectUser(@PathVariable UUID id) {
        return projectUserService.deleteProjectUser(id);
    }
}
