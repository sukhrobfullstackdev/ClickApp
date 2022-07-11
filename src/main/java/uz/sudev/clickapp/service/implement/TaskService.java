package uz.sudev.clickapp.service.implement;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.sudev.clickapp.entity.Task;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.TaskDTO;

import java.util.UUID;

public interface TaskService {
    ResponseEntity<Page<Task>> getTasks(int page, int size);

    ResponseEntity<Task> getTask(UUID id);

    ResponseEntity<Message> addTask(TaskDTO taskDTO);

    ResponseEntity<Message> editTask(TaskDTO taskDTO, UUID id);

    ResponseEntity<Message> deleteTask(UUID id);

    ResponseEntity<Message> changeStatus(UUID taskId, UUID statusId);
}
