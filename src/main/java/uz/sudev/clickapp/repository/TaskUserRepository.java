package uz.sudev.clickapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sudev.clickapp.entity.TaskUser;

import java.util.UUID;

@Repository
public interface TaskUserRepository extends JpaRepository<TaskUser, UUID> {
    boolean existsByTaskIdAndUserId(UUID task_id, UUID user_id);
    boolean existsByTaskIdAndUserIdAndIdNot(UUID task_id, UUID user_id, UUID id);
}
