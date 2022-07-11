package uz.sudev.clickapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sudev.clickapp.entity.View;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.ViewDTO;
import uz.sudev.clickapp.service.implement.ViewService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/view")
public class ViewController {
    final ViewService viewService;

    public ViewController(ViewService viewService) {
        this.viewService = viewService;
    }

    @GetMapping
    public ResponseEntity<Page<View>> getViews(@RequestParam int page, @RequestParam int size) {
        return viewService.getViews(page, size);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<View> getView(@PathVariable UUID id) {
        return viewService.getView(id);
    }

    @PostMapping
    public ResponseEntity<Message> addView(@RequestBody ViewDTO viewDTO) {
        return viewService.addView(viewDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Message> editView(@RequestBody ViewDTO viewDTO,@PathVariable UUID id) {
        return viewService.editView(viewDTO,id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Message> deleteView(@PathVariable UUID id) {
        return viewService.deleteView(id);
    }
}
