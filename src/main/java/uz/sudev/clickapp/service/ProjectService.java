package uz.sudev.clickapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.sudev.clickapp.entity.Project;
import uz.sudev.clickapp.repository.ProjectRepository;
import uz.sudev.clickapp.service.implement.ProjectImplement;

@Service
public class ProjectService implements ProjectImplement {
    final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public ResponseEntity<Page<Project>> getProjects(int page, int size) {
        return ResponseEntity.ok(projectRepository.findAll(PageRequest.of(page, size)));
    }
}
