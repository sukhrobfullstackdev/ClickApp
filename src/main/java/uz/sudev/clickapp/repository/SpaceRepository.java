package uz.sudev.clickapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sudev.clickapp.entity.Space;
import uz.sudev.clickapp.entity.Workspace;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpaceRepository extends JpaRepository<Space, UUID> {
    boolean existsByWorkspaceAndName(Workspace workspace, String name);
    boolean existsByWorkspaceAndNameAndIdNot(Workspace workspace, String name, UUID id);
    Page<Space> findAllByWorkspaceId(Long workspace_id, Pageable pageable);
    Optional<Space> findByIdAndWorkspaceId(UUID id, Long workspace_id);
}
