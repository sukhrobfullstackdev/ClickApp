package uz.sudev.clickapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sudev.clickapp.entity.View;

import java.util.UUID;

@Repository
public interface ViewRepository extends JpaRepository<View, UUID> {
    boolean existsByNameAndIconId(String name, Long icon_id);
    boolean existsByNameAndIconIdAndIdNot(String name, Long icon_id, UUID id);
}
