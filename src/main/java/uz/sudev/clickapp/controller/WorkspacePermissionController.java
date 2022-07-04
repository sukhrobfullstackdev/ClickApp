package uz.sudev.clickapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sudev.clickapp.entity.WorkspacePermission;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.WorkspacePermissionDTO;
import uz.sudev.clickapp.service.WorkspacePermissionService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/workspacePermission")
public class WorkspacePermissionController {
    final WorkspacePermissionService workspacePermissionService;

    public WorkspacePermissionController(WorkspacePermissionService workspacePermissionService) {
        this.workspacePermissionService = workspacePermissionService;
    }

    @GetMapping(value = "/{workspaceRoleId}")
    public ResponseEntity<List<WorkspacePermission>> getAllPermissions(@PathVariable UUID workspaceRoleId) {
        return workspacePermissionService.getAllPermissions(workspaceRoleId);
    }
    @PostMapping(value = "/{workspaceRoleId}")
    public ResponseEntity<Message> addPermissionsToRole(@PathVariable UUID workspaceRoleId,@RequestBody WorkspacePermissionDTO workspacePermissionDTO) {
        return workspacePermissionService.addPermissionsToRole(workspaceRoleId,workspacePermissionDTO);
    }
    @PutMapping(value = "/{workspaceRoleId}")
    public ResponseEntity<Message> editPermissionsToRole(@PathVariable UUID workspaceRoleId,@RequestBody WorkspacePermissionDTO workspacePermissionDTO) {
        return workspacePermissionService.editPermissionsToRole(workspaceRoleId,workspacePermissionDTO);
    }
    @DeleteMapping(value = "/{workspaceRoleId}")
    public ResponseEntity<Message> deletePermissionsToRole(@PathVariable UUID workspaceRoleId) {
        return workspacePermissionService.deletePermissionsToRole(workspaceRoleId);
    }
}
