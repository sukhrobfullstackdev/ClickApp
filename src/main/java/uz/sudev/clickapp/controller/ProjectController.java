package uz.sudev.clickapp.controller;


import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sudev.clickapp.entity.Project;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.ProjectDTO;
import uz.sudev.clickapp.service.ProjectService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {
    final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(value = "/{spaceId}")
    public ResponseEntity<Page<Project>> getProjects(@RequestParam int page, @RequestParam int size, @PathVariable UUID spaceId) {
        return projectService.getProjects(page, size, spaceId);
    }

    @GetMapping(value = "/{spaceId}/{projectId}")
    public ResponseEntity<Project> getProjects(@PathVariable Long projectId, @PathVariable UUID spaceId) {
        return projectService.getProject(projectId, spaceId);
    }

    @PostMapping
    public ResponseEntity<Message> addProject(@RequestBody ProjectDTO projectDTO) {
        return projectService.addProject(projectDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Message> editProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        return projectService.editProject(id, projectDTO);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Message> deleteProject(@PathVariable Long id) {
        return projectService.deleteProject(id);
    }
}
