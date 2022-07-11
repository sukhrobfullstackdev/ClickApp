package uz.sudev.clickapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.sudev.clickapp.entity.Comment;
import uz.sudev.clickapp.entity.Task;
import uz.sudev.clickapp.payload.CommentDTO;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.repository.CommentRepository;
import uz.sudev.clickapp.repository.TaskRepository;
import uz.sudev.clickapp.service.implement.CommentService;

import java.util.Optional;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {
    final CommentRepository commentRepository;
    final TaskRepository taskRepository;

    public CommentServiceImpl(CommentRepository commentRepository, TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public ResponseEntity<Page<Comment>> getComments(int page, int size) {
        return ResponseEntity.ok(commentRepository.findAll(PageRequest.of(page, size)));
    }

    @Override
    public ResponseEntity<Comment> getComment(UUID id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        return optionalComment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @Override
    public ResponseEntity<Message> addComment(CommentDTO commentDTO) {
        Optional<Task> optionalTask = taskRepository.findById(commentDTO.getTaskId());
        if (optionalTask.isPresent()) {
            commentRepository.save(new Comment(commentDTO.getName(), optionalTask.get()));
            return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "The comment has been successfully added!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The task is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> editComment(CommentDTO commentDTO, UUID id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        Optional<Task> optionalTask = taskRepository.findById(commentDTO.getTaskId());
        if (optionalComment.isPresent()) {
            if (optionalTask.isPresent()) {
                Comment comment = optionalComment.get();
                comment.setName(commentDTO.getName());
                comment.setTask(optionalTask.get());
                commentRepository.save(comment);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "The comment has been successfully edited!"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The task is not found!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The comment is nt found!"));
        }
    }

    @Override
    public ResponseEntity<Message> deleteComment(UUID id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            commentRepository.delete(optionalComment.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Message(true, "The comment has been successfully deleted!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The comment is not found!"));
        }
    }
}
