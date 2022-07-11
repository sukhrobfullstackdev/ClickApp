package uz.sudev.clickapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.sudev.clickapp.entity.Space;
import uz.sudev.clickapp.entity.SpaceView;
import uz.sudev.clickapp.entity.View;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.SpaceViewDTO;
import uz.sudev.clickapp.repository.SpaceRepository;
import uz.sudev.clickapp.repository.SpaceViewRepository;
import uz.sudev.clickapp.repository.ViewRepository;
import uz.sudev.clickapp.service.implement.SpaceViewService;

import java.util.Optional;
import java.util.UUID;

@Service
public class SpaceViewServiceImpl implements SpaceViewService {
    final SpaceViewRepository spaceViewRepository;
    final SpaceRepository spaceRepository;
    final ViewRepository viewRepository;

    public SpaceViewServiceImpl(SpaceViewRepository spaceViewRepository, SpaceRepository spaceRepository, ViewRepository viewRepository) {
        this.spaceViewRepository = spaceViewRepository;
        this.spaceRepository = spaceRepository;
        this.viewRepository = viewRepository;
    }

    @Override
    public ResponseEntity<Page<SpaceView>> getSpaceViews(int page, int size) {
        return ResponseEntity.ok(spaceViewRepository.findAll(PageRequest.of(page, size)));
    }

    @Override
    public ResponseEntity<SpaceView> getSpaceView(UUID id) {
        Optional<SpaceView> optionalSpaceView = spaceViewRepository.findById(id);
        return optionalSpaceView.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @Override
    public ResponseEntity<Message> addSpaceView(SpaceViewDTO spaceViewDTO) {
        Optional<Space> optionalSpace = spaceRepository.findById(spaceViewDTO.getSpaceId());
        Optional<View> optionalView = viewRepository.findById(spaceViewDTO.getViewId());
        if (optionalSpace.isPresent()) {
            if (optionalView.isPresent()) {
                if (!spaceViewRepository.existsByViewIdAndSpaceId(spaceViewDTO.getViewId(), spaceViewDTO.getSpaceId())) {
                    spaceViewRepository.save(new SpaceView(optionalSpace.get(), optionalView.get()));
                    return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "The view has been successfully created for this space!"));
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The view is already exists in this space!"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The view is not found!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The space is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> editSpaceView(SpaceViewDTO spaceViewDTO, UUID id) {
        Optional<SpaceView> optionalSpaceView = spaceViewRepository.findById(id);
        if (optionalSpaceView.isPresent()) {
            Optional<Space> optionalSpace = spaceRepository.findById(spaceViewDTO.getSpaceId());
            Optional<View> optionalView = viewRepository.findById(spaceViewDTO.getViewId());
            if (optionalSpace.isPresent()) {
                if (optionalView.isPresent()) {
                    if (!spaceViewRepository.existsByViewIdAndSpaceIdAndIdNot(spaceViewDTO.getViewId(), spaceViewDTO.getSpaceId(), id)) {
                        SpaceView spaceView = optionalSpaceView.get();
                        spaceView.setSpace(optionalSpace.get());
                        spaceView.setView(optionalView.get());
                        spaceViewRepository.save(spaceView);
                        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "The current view has been successfully edited in this space!"));
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The view is already exists in this space!"));
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The view is not found!"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The space is not found!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The space view is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> deleteSpaceView(UUID id) {
        Optional<SpaceView> optionalSpaceView = spaceViewRepository.findById(id);
        if (optionalSpaceView.isPresent()) {
            spaceViewRepository.delete(optionalSpaceView.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Message(true, "The space view has been successfully deleted!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The space view is not found!"));
        }
    }

    @Override
    public ResponseEntity<?> getViews(UUID spaceId, int page, int size) {
        Optional<Space> optionalSpace = spaceRepository.findById(spaceId);
        if (optionalSpace.isPresent()) {
            return ResponseEntity.ok(spaceViewRepository.findAllBySpaceId(spaceId, PageRequest.of(page, size)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The space is not found!"));
        }
    }
}
