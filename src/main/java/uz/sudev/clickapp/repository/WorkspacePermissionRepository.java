package uz.sudev.clickapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sudev.clickapp.entity.WorkspacePermission;
import uz.sudev.clickapp.entity.WorkspaceRole;
import uz.sudev.clickapp.entity.enums.WorkspacePermissionName;
import uz.sudev.clickapp.entity.enums.WorkspaceRoleName;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkspacePermissionRepository extends JpaRepository<WorkspacePermission, UUID> {
    List<WorkspacePermission> findAllByWorkspaceRoleId(UUID workspaceRole_id);
    List<WorkspacePermissionName> findAllByWorkspaceRole(WorkspaceRole workspaceRole);
    List<WorkspacePermission> findWorkspacePermissionsByWorkspaceRole_ExtendsRole(WorkspaceRoleName workspaceRole_extendsRole);
    List<WorkspacePermission> findAllByWorkspaceRole_NameAndWorkspaceRole_WorkspaceId(String workspaceRole_name, Long workspaceRole_workspace_id);
    void deleteAllByWorkspaceRoleId(UUID workspaceRole_id);
    Optional<WorkspacePermission> findByWorkspaceRoleIdAndPermission(UUID workspaceRole_id, WorkspacePermissionName permission);
}
