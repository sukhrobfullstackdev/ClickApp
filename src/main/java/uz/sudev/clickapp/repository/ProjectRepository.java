package uz.sudev.clickapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sudev.clickapp.entity.Project;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findAllBySpaceId(UUID space_id, Pageable pageable);
    Optional<Project> findBySpaceIdAndId(UUID space_id, Long id);
    boolean existsByNameAndSpaceId(String name, UUID space_id);
    boolean existsByNameAndSpaceIdAndIdNot(String name, UUID space_id, Long id);
}
