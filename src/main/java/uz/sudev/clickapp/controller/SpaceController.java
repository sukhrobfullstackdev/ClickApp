package uz.sudev.clickapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sudev.clickapp.annotations.CurrentUser;
import uz.sudev.clickapp.entity.Space;
import uz.sudev.clickapp.entity.User;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.SpaceDTO;
import uz.sudev.clickapp.service.SpaceService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/space")
public class SpaceController {
    final SpaceService spaceService;

    public SpaceController(SpaceService spaceService) {
        this.spaceService = spaceService;
    }

    @GetMapping
    public ResponseEntity<Page<Space>> getSpaces(@RequestParam Long workspaceId, @RequestParam int page, @RequestParam int size) {
        return spaceService.getSpaces(workspaceId, page, size);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Space> getSpaces(@RequestParam Long workspaceId,@PathVariable UUID id) {
        return spaceService.getSpace(workspaceId, id);
    }

    @PostMapping
    public ResponseEntity<Message> addSpace(@RequestBody SpaceDTO spaceDTO, @CurrentUser User user) {
        return spaceService.addSpace(spaceDTO, user);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Message> editSpace(@RequestBody SpaceDTO spaceDTO, @PathVariable UUID id) {
        return spaceService.editSpace(spaceDTO, id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Message> deleteSpace(@PathVariable UUID id) {
        return spaceService.deleteSpace(id);
    }
}
