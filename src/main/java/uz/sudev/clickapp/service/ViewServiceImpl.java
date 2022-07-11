package uz.sudev.clickapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.sudev.clickapp.entity.Icon;
import uz.sudev.clickapp.entity.View;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.ViewDTO;
import uz.sudev.clickapp.repository.IconRepository;
import uz.sudev.clickapp.repository.ViewRepository;
import uz.sudev.clickapp.service.implement.ViewService;

import java.util.Optional;
import java.util.UUID;

@Service
public class ViewServiceImpl implements ViewService {
    final ViewRepository viewRepository;
    final IconRepository iconRepository;

    public ViewServiceImpl(ViewRepository viewRepository, IconRepository iconRepository) {
        this.viewRepository = viewRepository;
        this.iconRepository = iconRepository;
    }

    @Override
    public ResponseEntity<Page<View>> getViews(int page, int size) {
        return ResponseEntity.ok(viewRepository.findAll(PageRequest.of(page, size)));
    }

    @Override
    public ResponseEntity<View> getView(UUID id) {
        Optional<View> optionalView = viewRepository.findById(id);
        return optionalView.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @Override
    public ResponseEntity<Message> addView(ViewDTO viewDTO) {
        Optional<Icon> optionalIcon = iconRepository.findById(viewDTO.getIconId());
        if (optionalIcon.isPresent()) {
            if (!viewRepository.existsByNameAndIconId(viewDTO.getName(), viewDTO.getIconId())) {
                viewRepository.save(new View(viewDTO.getName(), optionalIcon.get()));
                return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "The view has been successfully created!"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The view is already exists!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The icon is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> editView(ViewDTO viewDTO, UUID id) {
        Optional<View> optionalView = viewRepository.findById(id);
        if (optionalView.isPresent()) {
            Optional<Icon> optionalIcon = iconRepository.findById(viewDTO.getIconId());
            if (optionalIcon.isPresent()) {
                if (!viewRepository.existsByNameAndIconIdAndIdNot(viewDTO.getName(), viewDTO.getIconId(),id)) {
                    View view = optionalView.get();
                    view.setName(viewDTO.getName());
                    view.setIcon(optionalIcon.get());
                    viewRepository.save(view);
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "The view has been successfully edited!"));
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The view is already exists!"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The icon is not found!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false,"The view is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> deleteView(UUID id) {
        Optional<View> optionalView = viewRepository.findById(id);
        if (optionalView.isPresent()) {
            viewRepository.delete(optionalView.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Message(true,"The view has been successfully deleted!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false,"The view is not found!"));
        }
    }
}
