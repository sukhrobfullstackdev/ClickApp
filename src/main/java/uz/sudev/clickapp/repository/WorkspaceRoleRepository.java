package uz.sudev.clickapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sudev.clickapp.entity.Workspace;
import uz.sudev.clickapp.entity.WorkspaceRole;

import java.util.UUID;

@Repository
public interface WorkspaceRoleRepository extends JpaRepository<WorkspaceRole, UUID> {
    boolean existsByNameAndWorkspace(String name, Workspace workspace);
    boolean existsByNameAndWorkspaceAndIdNot(String name, Workspace workspace, UUID id);
}
