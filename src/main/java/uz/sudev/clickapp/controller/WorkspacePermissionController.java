package uz.sudev.clickapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.sudev.clickapp.entity.WorkspacePermission;
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
}
