package uz.sudev.clickapp.controller;


import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sudev.clickapp.entity.Status;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.StatusDTO;
import uz.sudev.clickapp.service.implement.StatusService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/status")
public class StatusController {
    final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public ResponseEntity<Page<Status>> getStatuses(@RequestParam int page, @RequestParam int size) {
        return statusService.getStatuses(page, size);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Status> getStatus(@PathVariable UUID id) {
        return statusService.getStatus(id);
    }

    @PostMapping
    public ResponseEntity<Message> addStatus(@RequestBody StatusDTO statusDTO) {
        return statusService.addStatus(statusDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Message> editStatus(@RequestBody StatusDTO statusDTO, @PathVariable UUID id) {
        return statusService.editStatus(statusDTO, id);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Message> deleteStatus(@PathVariable UUID id) {
        return statusService.deleteStatus(id);
    }
}
