package uz.sudev.clickapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sudev.clickapp.entity.User;
import uz.sudev.clickapp.entity.Workspace;

import java.util.UUID;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
    boolean existsByOwnerIdAndName(UUID owner_id, String name);
    Page<Workspace> findAllByOwner(User owner, Pageable pageable);

}
