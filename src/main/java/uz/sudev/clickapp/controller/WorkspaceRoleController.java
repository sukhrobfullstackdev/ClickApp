package uz.sudev.clickapp.controller;


import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sudev.clickapp.entity.WorkspaceRole;
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

    @GetMapping(value = "/{workspaceId}")
    public ResponseEntity<Page<WorkspaceRole>> getRoles(@RequestParam int page, @RequestParam int size, @PathVariable Long workspaceId) {
        return workspaceRoleService.getRoles(page, size, workspaceId);
    }
    @GetMapping(value = "/{workspaceId}/{roleId}")
    public ResponseEntity<WorkspaceRole> getRoles(@PathVariable Long workspaceId,@PathVariable UUID roleId) {
        return workspaceRoleService.getRole(workspaceId, roleId);
    }

    @PostMapping
    public ResponseEntity<Message> addRole(@RequestBody WorkspaceRoleDTO workspaceRoleDTO) {
        return workspaceRoleService.addRole(workspaceRoleDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Message> editRole(@PathVariable UUID id, @RequestBody WorkspaceRoleDTO workspaceRoleDTO) {
        return workspaceRoleService.editRole(id, workspaceRoleDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Message> deleteRole(@PathVariable UUID id) {
        return workspaceRoleService.deleteRole(id);
    }
}
