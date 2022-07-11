package uz.sudev.clickapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sudev.clickapp.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    boolean existsByNameAndWorkspaceId(String name, Long workspace_id);
    boolean existsByNameAndWorkspaceIdAndIdNot(String name, Long workspace_id, Long id);
}
