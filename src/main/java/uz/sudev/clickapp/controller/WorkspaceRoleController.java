package uz.sudev.clickapp.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.WorkspaceRoleDTO;
import uz.sudev.clickapp.service.WorkspaceRoleService;

@RestController
@RequestMapping(value = "/workspaceRole")
public class WorkspaceRoleController {
    final WorkspaceRoleService workspaceRoleService;

    public WorkspaceRoleController(WorkspaceRoleService workspaceRoleService) {
        this.workspaceRoleService = workspaceRoleService;
    }

    @PostMapping
    public ResponseEntity<Message> addRole(@RequestBody WorkspaceRoleDTO workspaceRoleDTO) {
        return workspaceRoleService.addRole(workspaceRoleDTO);
    }
}
