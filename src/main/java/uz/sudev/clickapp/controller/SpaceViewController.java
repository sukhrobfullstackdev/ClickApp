package uz.sudev.clickapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sudev.clickapp.entity.SpaceView;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.SpaceViewDTO;
import uz.sudev.clickapp.service.implement.SpaceViewService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/spaceView")
public class SpaceViewController {
    final SpaceViewService spaceViewService;

    public SpaceViewController(SpaceViewService spaceViewService) {
        this.spaceViewService = spaceViewService;
    }

    @GetMapping
    public ResponseEntity<Page<SpaceView>> getSpaceViews(@RequestParam int page, @RequestParam int size) {
        return spaceViewService.getSpaceViews(page, size);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SpaceView> getSpaceView(@PathVariable UUID id) {
        return spaceViewService.getSpaceView(id);
    }

    @GetMapping(value = "/getViews/{spaceId}")
    public ResponseEntity<?> getViews(@PathVariable UUID spaceId,@RequestParam int page,@RequestParam int size) {
        return spaceViewService.getViews(spaceId,page,size);
    }

    @PostMapping
    public ResponseEntity<Message> addSpaceView(@RequestBody SpaceViewDTO spaceViewDTO) {
        return spaceViewService.addSpaceView(spaceViewDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Message> editSpaceView(@RequestBody SpaceViewDTO spaceViewDTO, @PathVariable UUID id) {
        return spaceViewService.editSpaceView(spaceViewDTO, id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Message> deleteSpaceView(@PathVariable UUID id) {
        return spaceViewService.deleteSpaceView(id);
    }
}
