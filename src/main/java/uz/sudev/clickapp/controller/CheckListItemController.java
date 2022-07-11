package uz.sudev.clickapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sudev.clickapp.entity.CheckListItem;
import uz.sudev.clickapp.payload.CheckListItemDTO;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.service.implement.CheckListItemService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/checkListItem")
public class CheckListItemController {
    final CheckListItemService checkListItemService;

    public CheckListItemController(CheckListItemService checkListItemService) {
        this.checkListItemService = checkListItemService;
    }

    @GetMapping
    public ResponseEntity<Page<CheckListItem>> getCheckListItems(@RequestParam int page, @RequestParam int size) {
        return checkListItemService.getCheckListItems(page, size);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CheckListItem> getCheckListItem(@PathVariable UUID id) {
        return checkListItemService.getCheckListItem(id);
    }

    @PostMapping
    public ResponseEntity<Message> addCheckListItem(@RequestBody CheckListItemDTO checkListItemDTO) {
        return checkListItemService.addCheckListItem(checkListItemDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Message> editCheckListItem(@RequestBody CheckListItemDTO checkListItemDTO, @PathVariable UUID id) {
        return checkListItemService.editCheckListItem(checkListItemDTO, id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Message> deleteCheckListItem(@PathVariable UUID id) {
        return checkListItemService.deleteCheckListItem(id);
    }
}
