package uz.sudev.clickapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.sudev.clickapp.annotations.CurrentUser;
import uz.sudev.clickapp.entity.User;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.WorkspaceDTO;
import uz.sudev.clickapp.service.WorkspaceService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/workspace")
public class WorkspaceController {
    final WorkspaceService workspaceService;

    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @PostMapping
    public ResponseEntity<Message> addWorkspace(@Valid @RequestBody WorkspaceDTO workspaceDTO, @CurrentUser User user) {
        return workspaceService.addWorkspace(workspaceDTO,user);
    }
}
