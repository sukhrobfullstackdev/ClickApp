package uz.sudev.clickapp.controller;


import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sudev.clickapp.annotations.CurrentUser;
import uz.sudev.clickapp.entity.User;
import uz.sudev.clickapp.entity.Workspace;
import uz.sudev.clickapp.payload.MemberDTO;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.WorkspaceDTO;
import uz.sudev.clickapp.payload.WorkspaceRoleDTO;
import uz.sudev.clickapp.service.WorkspaceService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = "/workspace")
public class WorkspaceController {
    final WorkspaceService workspaceService;

    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @GetMapping
    public ResponseEntity<Page<Workspace>> getWorkspaces(@RequestParam int page, @RequestParam int size) {
        return workspaceService.getWorkspaces(page, size);
    }
    @GetMapping(value = "/getOwnWorkspaces")
    public ResponseEntity<Page<Workspace>> getMyWorkspaces(@RequestParam int page, @RequestParam int size,@CurrentUser User user) {
        return workspaceService.getMyWorkspaces(page, size,user);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Workspace> getWorkspace(@PathVariable Long id) {
        return workspaceService.getWorkspace(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Message> editWorkspace(@PathVariable Long id, @RequestBody WorkspaceDTO workspaceDTO) {
        return workspaceService.editWorkspace(id, workspaceDTO);
    }

    @PutMapping(value = "/changeOwner")
    public ResponseEntity<Message> changeOwner(@RequestParam Long id, @RequestParam UUID ownerId) {
        return workspaceService.changeOwner(id, ownerId);
    }

    @PostMapping
    public ResponseEntity<Message> addWorkspace(@Valid @RequestBody WorkspaceDTO workspaceDTO, @CurrentUser User user) {
        return workspaceService.addWorkspace(workspaceDTO, user);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Message> deleteWorkspace(@PathVariable Long id) {
        return workspaceService.deleteWorkspace(id);
    }

    @PostMapping(value = "/addOrEditOrRemoveMemberOfWorkspace/{workspaceId}")
    public ResponseEntity<Message> addOrEditOrRemoveMemberOfWorkspace(@PathVariable Long workspaceId, @RequestBody MemberDTO memberDTO) {
        return workspaceService.addOrEditOrRemoveMemberOfWorkspace(workspaceId, memberDTO);
    }

    @PutMapping(value = "/joinToWorkspace")
    public ResponseEntity<Message> joinToWorkspace(@RequestParam Long workspaceId, @CurrentUser User user) {
        return workspaceService.joinToWorkspace(workspaceId, user);
    }
    @PutMapping(value = "/addOrRemovePermission")
    public ResponseEntity<Message> addOrRemovePermissionToRole(@RequestBody WorkspaceRoleDTO workspaceRoleDTO){
        return workspaceService.addOrRemovePermissionToRole(workspaceRoleDTO);
    }
}
