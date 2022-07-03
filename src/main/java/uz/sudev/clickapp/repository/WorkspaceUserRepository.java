package uz.sudev.clickapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.sudev.clickapp.entity.WorkspaceUser;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkspaceUserRepository extends JpaRepository<WorkspaceUser, UUID> {
    Optional<WorkspaceUser> findByUserIdAndWorkspaceId(UUID user_id, Long workspace_id);
    @Transactional
    @Modifying // shunchaki ishni bajarsin bizga natijasi kere mas!
    void deleteByUserIdAndWorkspaceId(UUID user_id, Long workspace_id);
}
