package uz.sudev.clickapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.sudev.clickapp.entity.Tag;
import uz.sudev.clickapp.entity.Workspace;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.TagDTO;
import uz.sudev.clickapp.repository.TagRepository;
import uz.sudev.clickapp.repository.WorkspaceRepository;
import uz.sudev.clickapp.service.implement.TagService;

import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {
    final TagRepository tagRepository;
    final WorkspaceRepository workspaceRepository;

    public TagServiceImpl(TagRepository tagRepository, WorkspaceRepository workspaceRepository) {
        this.tagRepository = tagRepository;
        this.workspaceRepository = workspaceRepository;
    }

    @Override
    public ResponseEntity<Page<Tag>> getTags(int page, int size) {
        return ResponseEntity.ok(tagRepository.findAll(PageRequest.of(page, size)));
    }

    @Override
    public ResponseEntity<Tag> getTag(Long id) {
        Optional<Tag> optionalTag = tagRepository.findById(id);
        return optionalTag.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @Override
    public ResponseEntity<Message> addTag(TagDTO tagDTO) {
        Optional<Workspace> optionalWorkspace = workspaceRepository.findById(tagDTO.getWorkspaceId());
        if (optionalWorkspace.isPresent()) {
            if (tagRepository.existsByNameAndWorkspaceId(tagDTO.getName(), tagDTO.getWorkspaceId())) {
                tagRepository.save(new Tag(tagDTO.getName(), tagDTO.getColor(), optionalWorkspace.get()));
                return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "The tag has been successfully added!"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The tag is already exists!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The workspace is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> editTag(TagDTO tagDTO, Long id) {
        Optional<Tag> optionalTag = tagRepository.findById(id);
        if (optionalTag.isPresent()) {
            Optional<Workspace> optionalWorkspace = workspaceRepository.findById(tagDTO.getWorkspaceId());
            if (optionalWorkspace.isPresent()) {
                if (tagRepository.existsByNameAndWorkspaceIdAndIdNot(tagDTO.getName(), tagDTO.getWorkspaceId(), id)) {
                    Tag tag = optionalTag.get();
                    tag.setColor(tagDTO.getColor());
                    tag.setName(tagDTO.getName());
                    tag.setWorkspace(optionalWorkspace.get());
                    tagRepository.save(tag);
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "The tag has been successfully edited!"));
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The tag is already exists!"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The workspace is not found!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The tag is not found!"));
        }
    }

    @Override
    public ResponseEntity<Message> deleteTag(Long id) {
        Optional<Tag> optionalTag = tagRepository.findById(id);
        if (optionalTag.isPresent()) {
            tagRepository.delete(optionalTag.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Message(true, "The tag has been successfully deleted!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The tag is not found!"));
        }
    }
}
