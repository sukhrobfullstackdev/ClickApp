package uz.sudev.clickapp.service.implement;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.sudev.clickapp.entity.TaskAttachment;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.TaskAttachmentDTO;

import java.util.UUID;

public interface TaskAttachmentService {
    ResponseEntity<Page<TaskAttachment>> getTaskAttachments(int page, int size);

    ResponseEntity<TaskAttachment> getTaskAttachment(UUID id);

    ResponseEntity<Message> addTaskAttachment(TaskAttachmentDTO taskAttachmentDTO);

    ResponseEntity<Message> editTaskAttachment(TaskAttachmentDTO taskAttachmentDTO, UUID id);

    ResponseEntity<Message> deleteTaskAttachment(UUID id);
}
