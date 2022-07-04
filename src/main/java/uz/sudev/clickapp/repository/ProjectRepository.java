package uz.sudev.clickapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sudev.clickapp.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
