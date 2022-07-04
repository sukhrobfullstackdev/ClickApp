package uz.sudev.clickapp.controller;


import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sudev.clickapp.entity.Project;
import uz.sudev.clickapp.service.ProjectService;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {
    final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

//    @GetMapping(value = "/{spaceId}")
//    public ResponseEntity<Page<Project>> getProjects(@RequestParam int page, @RequestParam int size, @PathVariable ) {
//        return projectService.getProjects(page,size);
//    }
}
