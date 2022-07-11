package uz.sudev.clickapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sudev.clickapp.entity.TaskAttachment;

import java.util.UUID;

@Repository
public interface TaskAttachmentRepository extends JpaRepository<TaskAttachment, UUID> {
    boolean existsByAttachmentIdAndTaskId(UUID attachment_id, UUID task_id);
    boolean existsByAttachmentIdAndTaskIdAndIdNot(UUID attachment_id, UUID task_id, UUID id);
}
