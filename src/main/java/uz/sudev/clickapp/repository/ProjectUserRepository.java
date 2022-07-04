package uz.sudev.clickapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sudev.clickapp.entity.ProjectUser;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUser, UUID> {
    boolean existsByUserIdAndProjectId(UUID user_id, Long project_id);
    boolean existsByUserIdAndProjectIdAndIdNot(UUID user_id, Long project_id, UUID id);
    Page<ProjectUser> findAllByProjectId(Long project_id, Pageable pageable);
    Optional<ProjectUser> findByUserIdAndProjectId(UUID user_id, Long project_id);
}
