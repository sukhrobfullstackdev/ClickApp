package uz.sudev.clickapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sudev.clickapp.entity.Tag;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.TagDTO;
import uz.sudev.clickapp.service.implement.TagService;

@RestController
@RequestMapping(value = "/tag")
public class TagController {
    final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<Page<Tag>> getTags(@RequestParam int page, @RequestParam int size) {
        return tagService.getTags(page, size);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Tag> getTag(@PathVariable Long id) {
        return tagService.getTag(id);
    }

    @PostMapping
    public ResponseEntity<Message> addTag(@RequestBody TagDTO tagDTO) {
        return tagService.addTag(tagDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Message> editTag(@RequestBody TagDTO tagDTO,@PathVariable Long id) {
        return tagService.editTag(tagDTO,id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Message> deleteTag(@PathVariable Long id) {
        return tagService.deleteTag(id);
    }
}
