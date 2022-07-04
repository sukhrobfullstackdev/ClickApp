package uz.sudev.clickapp.service.implement;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.sudev.clickapp.entity.Project;

public interface ProjectImplement {
    ResponseEntity<Page<Project>> getProjects(int page, int size);
}
