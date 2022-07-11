package uz.sudev.clickapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sudev.clickapp.entity.TaskTag;

import java.util.UUID;

@Repository
public interface TaskTagRepository extends JpaRepository<TaskTag, UUID> {
    boolean existsByTaskIdAndTagId(UUID task_id, Long tag_id);
    boolean existsByTaskIdAndTagIdAndIdNot(UUID task_id, Long tag_id, UUID id);
}
