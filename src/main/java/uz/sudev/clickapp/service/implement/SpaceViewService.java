package uz.sudev.clickapp.service.implement;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.sudev.clickapp.entity.SpaceView;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.SpaceViewDTO;

import java.util.UUID;

public interface SpaceViewService {
    ResponseEntity<Page<SpaceView>> getSpaceViews(int page, int size);

    ResponseEntity<SpaceView> getSpaceView(UUID id);

    ResponseEntity<Message> addSpaceView(SpaceViewDTO spaceViewDTO);

    ResponseEntity<Message> editSpaceView(SpaceViewDTO spaceViewDTO, UUID id);

    ResponseEntity<Message> deleteSpaceView(UUID id);

    ResponseEntity<?> getViews(UUID spaceId, int page, int size);
}
