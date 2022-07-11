package uz.sudev.clickapp.service.implement;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.sudev.clickapp.entity.View;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.ViewDTO;

import java.util.UUID;

public interface ViewService {
    ResponseEntity<Page<View>> getViews(int page, int size);

    ResponseEntity<View> getView(UUID id);

    ResponseEntity<Message> addView(ViewDTO viewDTO);

    ResponseEntity<Message> editView(ViewDTO viewDTO, UUID id);

    ResponseEntity<Message> deleteView(UUID id);
}
