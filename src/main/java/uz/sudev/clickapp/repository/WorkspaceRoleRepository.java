package uz.sudev.clickapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sudev.clickapp.entity.Workspace;
import uz.sudev.clickapp.entity.WorkspacePermission;
import uz.sudev.clickapp.entity.WorkspaceRole;
import uz.sudev.clickapp.entity.enums.WorkspaceRoleName;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkspaceRoleRepository extends JpaRepository<WorkspaceRole, UUID> {
    boolean existsByNameAndWorkspace(String name, Workspace workspace);
    boolean existsByNameAndWorkspaceAndIdNot(String name, Workspace workspace, UUID id);
    Page<WorkspaceRole> findAllByWorkspaceId(Long workspace_id, Pageable pageable);
    Optional<WorkspaceRole> findByIdAndWorkspaceId(UUID id, Long workspace_id);
    Optional<WorkspaceRole> findByExtendsRole(WorkspaceRoleName extendsRole);
}
