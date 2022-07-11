package uz.sudev.clickapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.sudev.clickapp.entity.CheckList;
import uz.sudev.clickapp.entity.CheckListItem;
import uz.sudev.clickapp.entity.User;
import uz.sudev.clickapp.payload.CheckListItemDTO;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.repository.CheckListItemRepository;
import uz.sudev.clickapp.repository.CheckListRepository;
import uz.sudev.clickapp.repository.UserRepository;
import uz.sudev.clickapp.service.implement.CheckListItemService;

import java.util.Optional;
import java.util.UUID;

@Service
public class CheckListItemServiceImpl implements CheckListItemService {
    final CheckListItemRepository checkListItemRepository;
    final CheckListRepository checkListRepository;
    final UserRepository userRepository;

    public CheckListItemServiceImpl(CheckListItemRepository checkListItemRepository, CheckListRepository checkListRepository, UserRepository userRepository) {
        this.checkListItemRepository = checkListItemRepository;
        this.checkListRepository = checkListRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<Page<CheckListItem>> getCheckListItems(int page, int size) {
        return ResponseEntity.ok(checkListItemRepository.findAll(PageRequest.of(page, size)));
    }

    @Override
    public ResponseEntity<CheckListItem> getCheckListItem(UUID id) {
        Optional<CheckListItem> optionalCheckListItem = checkListItemRepository.findById(id);
        return optionalCheckListItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @Override
    public ResponseEntity<Message> addCheckListItem(CheckListItemDTO checkListItemDTO) {
        Optional<User> optionalUser = userRepository.findById(checkListItemDTO.getAssignedUserId());
        Optional<CheckList> optionalCheckList = checkListRepository.findById(checkListItemDTO.getCheckListId());
        if (optionalUser.isPresent()) {
            if (optionalCheckList.isPresent()) {
                if (!checkListItemRepository.existsByNameAndAssignedUserIdAndCheckListId(checkListItemDTO.getName(), checkListItemDTO.getAssignedUserId(), checkListItemDTO.getCheckListId())) {
                    checkListItemRepository.save(new CheckListItem(checkListItemDTO.getName(), optionalCheckList.get(), checkListItemDTO.isResolved(), optionalUser.get()));
                    return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "The check list item has been successfully added!"));
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The check list item is already exists!"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The check list is not found!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The user is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> editCheckListItem(CheckListItemDTO checkListItemDTO, UUID id) {
        Optional<CheckListItem> optionalCheckListItem = checkListItemRepository.findById(id);
        if (optionalCheckListItem.isPresent()) {
            Optional<User> optionalUser = userRepository.findById(checkListItemDTO.getAssignedUserId());
            Optional<CheckList> optionalCheckList = checkListRepository.findById(checkListItemDTO.getCheckListId());
            if (optionalUser.isPresent()) {
                if (optionalCheckList.isPresent()) {
                    if (!checkListItemRepository.existsByNameAndAssignedUserIdAndCheckListIdAndIdNot(checkListItemDTO.getName(), checkListItemDTO.getAssignedUserId(), checkListItemDTO.getCheckListId(), id)) {
                        CheckListItem checkListItem = optionalCheckListItem.get();
                        checkListItem.setName(checkListItemDTO.getName());
                        checkListItem.setCheckList(optionalCheckList.get());
                        checkListItem.setAssignedUser(optionalUser.get());
                        checkListItem.setResolved(checkListItemDTO.isResolved());
                        checkListItemRepository.save(checkListItem);
                        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "The check list item has been successfully edited!"));
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The check list item is already exists!"));
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The check list is not found!"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The user is not found!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The check list item is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> deleteCheckListItem(UUID id) {
        Optional<CheckListItem> optionalCheckListItem = checkListItemRepository.findById(id);
        if (optionalCheckListItem.isPresent()) {
            checkListItemRepository.save(optionalCheckListItem.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Message(true, "The check list item has been successfully deleted!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The check list item is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> assignUser(UUID checkListItemId, UUID userId) {
        Optional<CheckListItem> optionalCheckListItem = checkListItemRepository.findById(checkListItemId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalCheckListItem.isPresent()) {
            if (optionalUser.isPresent()) {
                CheckListItem checkListItem = optionalCheckListItem.get();
                checkListItem.setAssignedUser(optionalUser.get());
                checkListItemRepository.save(checkListItem);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "The user has been successfully assigned to this check list item!"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The user is not found!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The check list item is not found!"));
        }
    }
}
