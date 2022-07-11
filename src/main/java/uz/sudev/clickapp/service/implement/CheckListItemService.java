package uz.sudev.clickapp.service.implement;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.sudev.clickapp.entity.CheckListItem;
import uz.sudev.clickapp.payload.CheckListItemDTO;
import uz.sudev.clickapp.payload.Message;

import java.util.UUID;

public interface CheckListItemService {
    ResponseEntity<Page<CheckListItem>> getCheckListItems(int page, int size);

    ResponseEntity<CheckListItem> getCheckListItem(UUID id);

    ResponseEntity<Message> addCheckListItem(CheckListItemDTO checkListItemDTO);

    ResponseEntity<Message> editCheckListItem(CheckListItemDTO checkListItemDTO, UUID id);

    ResponseEntity<Message> deleteCheckListItem(UUID id);
}
