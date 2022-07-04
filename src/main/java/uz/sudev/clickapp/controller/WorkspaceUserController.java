package uz.sudev.clickapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sudev.clickapp.entity.WorkspaceUser;
import uz.sudev.clickapp.service.WorkspaceUserService;

@RestController
@RequestMapping(value = "/workspaceUser")
public class WorkspaceUserController {
    final WorkspaceUserService workspaceUserService;

    public WorkspaceUserController(WorkspaceUserService workspaceUserService) {
        this.workspaceUserService = workspaceUserService;
    }

    @GetMapping(value = "/getGuests/{workspaceId}")
    public ResponseEntity<Page<WorkspaceUser>> getWorkspaceGuests(@RequestParam int page, @RequestParam int size, @PathVariable Long workspaceId) {
        return workspaceUserService.getWorkspaceGuests(page,size,workspaceId);
    }
    @GetMapping(value = "/getMembers/{workspaceId}")
    public ResponseEntity<Page<WorkspaceUser>> getWorkspaceMembers(@RequestParam int page, @RequestParam int size, @PathVariable Long workspaceId) {
        return workspaceUserService.getWorkspaceMembers(page,size,workspaceId);
    }
}
