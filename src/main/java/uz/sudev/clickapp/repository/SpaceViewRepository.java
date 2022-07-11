package uz.sudev.clickapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.sudev.clickapp.entity.SpaceView;
import java.util.UUID;

public interface SpaceViewRepository extends JpaRepository<SpaceView, UUID> {
    boolean existsByViewIdAndSpaceId(UUID view_id, UUID space_id);
    boolean existsByViewIdAndSpaceIdAndIdNot(UUID view_id, UUID space_id, UUID id);

    Page<SpaceView> findAllBySpaceId(UUID space_id, Pageable pageable);
}
