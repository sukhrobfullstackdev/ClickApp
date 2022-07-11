package uz.sudev.clickapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sudev.clickapp.entity.Task;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    boolean existsByNameAndCategoryId(String name, Long category_id);
    boolean existsByNameAndCategoryIdAndIdNot(String name, Long category_id, UUID id);
}
