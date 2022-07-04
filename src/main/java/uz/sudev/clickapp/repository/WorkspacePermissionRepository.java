package uz.sudev.clickapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sudev.clickapp.entity.WorkspacePermission;

import java.util.List;
import java.util.UUID;

@Repository
public interface WorkspacePermissionRepository extends JpaRepository<WorkspacePermission, UUID> {
    List<WorkspacePermission> findAllByWorkspaceRoleId(UUID workspaceRole_id);
}
