package uz.sudev.clickapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.sudev.clickapp.entity.User;
import uz.sudev.clickapp.entity.Workspace;
import uz.sudev.clickapp.entity.WorkspaceUser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkspaceUserRepository extends JpaRepository<WorkspaceUser, UUID> {
    Optional<WorkspaceUser> findByUserIdAndWorkspaceId(UUID user_id, Long workspace_id);
    Page<WorkspaceUser> findAllByWorkspaceIdAndWorkspaceRole_Name(Long workspace_id, String workspaceRole_name, Pageable pageable);
    @Transactional
    @Modifying // shunchaki ishni bajarsin bizga natijasi kere mas!
    void deleteByUserIdAndWorkspaceId(UUID user_id, Long workspace_id);
    List<WorkspaceUser> findAllByWorkspaceId(Long workspace_id);
}
