package uz.sudev.clickapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sudev.clickapp.entity.CheckList;
import uz.sudev.clickapp.payload.CheckListDTO;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.service.implement.CheckListService;

@RestController
@RequestMapping(value = "/checkList")
public class CheckListController {
    final CheckListService checkListService;

    public CheckListController(CheckListService checkListService) {
        this.checkListService = checkListService;
    }

    @GetMapping
    public ResponseEntity<Page<CheckList>> getCheckLists(@RequestParam int page, @RequestParam int size) {
        return checkListService.getCheckLists(page, size);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CheckList> getCheckList(@PathVariable Long id) {
        return checkListService.getCheckList(id);
    }

    @PostMapping
    public ResponseEntity<Message> addCheckList(@RequestBody CheckListDTO checkListDTO) {
        return checkListService.addCheckList(checkListDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Message> editCheckList(@RequestBody CheckListDTO checkListDTO,@PathVariable Long id) {
        return checkListService.editCheckList(checkListDTO,id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Message> deleteCheckList(@PathVariable Long id) {
        return checkListService.deleteCheckList(id);
    }
}
