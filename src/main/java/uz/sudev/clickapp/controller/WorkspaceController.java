package uz.sudev.clickapp.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sudev.clickapp.annotations.CurrentUser;
import uz.sudev.clickapp.entity.User;
import uz.sudev.clickapp.payload.MemberDTO;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.WorkspaceDTO;
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

    @PostMapping
    public ResponseEntity<Message> addWorkspace(@Valid @RequestBody WorkspaceDTO workspaceDTO, @CurrentUser User user) {
        return workspaceService.addWorkspace(workspaceDTO, user);
    }

    @PostMapping(value = "/addOrEditOrRemoveMemberOfWorkspace/{workspaceId}")
    public ResponseEntity<Message> addOrEditOrRemoveMemberOfWorkspace(@PathVariable Long workspaceId, @RequestBody MemberDTO memberDTO) {
        return workspaceService.addOrEditOrRemoveMemberOfWorkspace(workspaceId,memberDTO);
    }
}
