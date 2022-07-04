package uz.sudev.clickapp.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.WorkspaceRoleDTO;
import uz.sudev.clickapp.service.WorkspaceRoleService;

import java.util.UUID;

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

    @PutMapping(value = "/{id}")
    public ResponseEntity<Message> editRole(@PathVariable UUID id, @RequestBody WorkspaceRoleDTO workspaceRoleDTO) {
        return workspaceRoleService.editRole(id, workspaceRoleDTO);
    }
}
