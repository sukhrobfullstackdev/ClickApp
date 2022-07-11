package uz.sudev.clickapp.service.implement;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.sudev.clickapp.entity.Status;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.StatusDTO;

import java.util.UUID;

public interface StatusService {
    ResponseEntity<Page<Status>> getStatuses(int page, int size);

    ResponseEntity<Status> getStatus(UUID id);

    ResponseEntity<Message> addStatus(StatusDTO statusDTO);

    ResponseEntity<Message> editStatus(StatusDTO statusDTO, UUID id);

    ResponseEntity<Message> deleteStatus(UUID id);
}
