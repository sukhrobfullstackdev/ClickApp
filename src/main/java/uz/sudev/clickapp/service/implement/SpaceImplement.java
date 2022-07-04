package uz.sudev.clickapp.service.implement;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.sudev.clickapp.entity.Space;
import uz.sudev.clickapp.entity.User;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.SpaceDTO;

import java.util.UUID;

public interface SpaceImplement {
    ResponseEntity<Message> addSpace(SpaceDTO spaceDTO, User user);

    ResponseEntity<Message> editSpace(SpaceDTO spaceDTO, UUID id);

    ResponseEntity<Message> deleteSpace(UUID id);
    ResponseEntity<Page<Space>> getSpaces(Long workspaceId,int page, int size);

    ResponseEntity<Space> getSpace(Long workspaceId, UUID id);
}
