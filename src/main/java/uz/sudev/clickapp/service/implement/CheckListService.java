package uz.sudev.clickapp.service.implement;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.sudev.clickapp.entity.CheckList;
import uz.sudev.clickapp.payload.CheckListDTO;
import uz.sudev.clickapp.payload.Message;

public interface CheckListService {
    ResponseEntity<Page<CheckList>> getCheckLists(int page, int size);
    ResponseEntity<CheckList> getCheckList(Long id);

    ResponseEntity<Message> addCheckList(CheckListDTO checkListDTO);

    ResponseEntity<Message> editCheckList(CheckListDTO checkListDTO, Long id);

    ResponseEntity<Message> deleteCheckList(Long id);
}
