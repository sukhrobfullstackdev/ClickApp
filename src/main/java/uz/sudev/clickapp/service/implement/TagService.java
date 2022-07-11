package uz.sudev.clickapp.service.implement;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.sudev.clickapp.entity.Tag;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.TagDTO;

public interface TagService {
    ResponseEntity<Page<Tag>> getTags(int page, int size);

    ResponseEntity<Tag> getTag(Long id);

    ResponseEntity<Message> addTag(TagDTO tagDTO);

    ResponseEntity<Message> editTag(TagDTO tagDTO, Long id);

    ResponseEntity<Message> deleteTag(Long id);
}
