package uz.sudev.clickapp.service.implement;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.sudev.clickapp.entity.TaskTag;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.TaskTagDTO;

import java.util.UUID;

public interface TaskTagService {
    ResponseEntity<Page<TaskTag>> getTaskTags(int page, int size);

    ResponseEntity<TaskTag> getTaskTag(UUID id);

    ResponseEntity<Message> addTaskTag(TaskTagDTO taskTagDTO);

    ResponseEntity<Message> editTaskTag(TaskTagDTO taskTagDTO, UUID id);

    ResponseEntity<Message> deleteTaskTag(UUID id);
}
