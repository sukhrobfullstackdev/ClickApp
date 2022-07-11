package uz.sudev.clickapp.service.implement;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.sudev.clickapp.entity.Comment;
import uz.sudev.clickapp.payload.CommentDTO;
import uz.sudev.clickapp.payload.Message;

import java.util.UUID;

public interface CommentService {
    ResponseEntity<Page<Comment>> getComments(int page, int size);

    ResponseEntity<Comment> getComment(UUID id);

    ResponseEntity<Message> addComment(CommentDTO commentDTO);

    ResponseEntity<Message> editComment(CommentDTO commentDTO, UUID id);

    ResponseEntity<Message> deleteComment(UUID id);
}
