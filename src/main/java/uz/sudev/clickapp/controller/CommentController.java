package uz.sudev.clickapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sudev.clickapp.entity.Comment;
import uz.sudev.clickapp.payload.CommentDTO;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.service.implement.CommentService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {
    final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<Page<Comment>> getComments(@RequestParam int page, @RequestParam int size) {
        return commentService.getComments(page, size);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable UUID id) {
        return commentService.getComment(id);
    }

    @PostMapping
    public ResponseEntity<Message> addComment(@RequestBody CommentDTO commentDTO) {
        return commentService.addComment(commentDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Message> editComment(@RequestBody CommentDTO commentDTO, @PathVariable UUID id) {
        return commentService.editComment(commentDTO, id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Message> deleteComment(@PathVariable UUID id) {
        return commentService.deleteComment(id);
    }
}
