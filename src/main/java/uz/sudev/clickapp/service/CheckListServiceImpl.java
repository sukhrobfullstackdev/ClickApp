package uz.sudev.clickapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.sudev.clickapp.entity.CheckList;
import uz.sudev.clickapp.entity.Task;
import uz.sudev.clickapp.payload.CheckListDTO;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.repository.CheckListRepository;
import uz.sudev.clickapp.repository.TaskRepository;
import uz.sudev.clickapp.service.implement.CheckListService;

import java.util.Optional;

@Service
public class CheckListServiceImpl implements CheckListService {
    final CheckListRepository checkListRepository;
    final TaskRepository taskRepository;

    public CheckListServiceImpl(CheckListRepository checkListRepository, TaskRepository taskRepository) {
        this.checkListRepository = checkListRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public ResponseEntity<Page<CheckList>> getCheckLists(int page, int size) {
        return ResponseEntity.ok(checkListRepository.findAll(PageRequest.of(page, size)));
    }

    @Override
    public ResponseEntity<CheckList> getCheckList(Long id) {
        Optional<CheckList> optionalCheckList = checkListRepository.findById(id);
        return optionalCheckList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @Override
    public ResponseEntity<Message> addCheckList(CheckListDTO checkListDTO) {
        Optional<Task> optionalTask = taskRepository.findById(checkListDTO.getTaskId());
        if (optionalTask.isPresent()) {
            if (!checkListRepository.existsByNameAndTaskId(checkListDTO.getName(), checkListDTO.getTaskId())) {
                checkListRepository.save(new CheckList(checkListDTO.getName(), optionalTask.get()));
                return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "The check list has been successfully created!"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The check list by this name is already exists in this task!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The task is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> editCheckList(CheckListDTO checkListDTO, Long id) {
        Optional<CheckList> optionalCheckList = checkListRepository.findById(id);
        if (optionalCheckList.isPresent()) {
            Optional<Task> optionalTask = taskRepository.findById(checkListDTO.getTaskId());
            if (optionalTask.isPresent()) {
                if (!checkListRepository.existsByNameAndTaskIdAndIdNot(checkListDTO.getName(), checkListDTO.getTaskId(), id)) {
                    CheckList checkList = optionalCheckList.get();
                    checkList.setTask(optionalTask.get());
                    checkList.setName(checkListDTO.getName());
                    checkListRepository.save(checkList);
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "The check list has been successfully edited!"));
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The check list by this name is already exists in this task!"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The task is not found!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The check list is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> deleteCheckList(Long id) {
        Optional<CheckList> optionalCheckList = checkListRepository.findById(id);
        if (optionalCheckList.isPresent()) {
            checkListRepository.delete(optionalCheckList.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Message(true, "The check list has been successfully deleted!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The check list is not found!"));
        }
    }
}
