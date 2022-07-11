package uz.sudev.clickapp.service.implement;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.sudev.clickapp.entity.TaskUser;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.TaskUserDTO;

import java.util.UUID;

public interface TaskUserService {
    ResponseEntity<Page<TaskUser>> getTaskUsers(int page, int size);

    ResponseEntity<TaskUser> getTaskUser(UUID id);

    ResponseEntity<Message> addTaskUser(TaskUserDTO taskUserDTO);

    ResponseEntity<Message> editTaskUser(TaskUserDTO taskUserDTO, UUID id);

    ResponseEntity<Message> deleteTaskUser(UUID id);
}
