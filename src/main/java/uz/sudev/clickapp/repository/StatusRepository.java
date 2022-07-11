package uz.sudev.clickapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sudev.clickapp.entity.Status;

import java.util.UUID;

@Repository
public interface StatusRepository extends JpaRepository<Status, UUID> {
    boolean existsByNameAndSpaceIdAndProjectId(String name, UUID space_id, Long project_id);

    boolean existsByNameAndSpaceIdAndProjectIdAndIdNot(String name, UUID space_id, Long project_id, UUID id);
}
