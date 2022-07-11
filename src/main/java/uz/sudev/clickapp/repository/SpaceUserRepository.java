package uz.sudev.clickapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sudev.clickapp.entity.SpaceUser;

import java.util.UUID;

@Repository
public interface SpaceUserRepository extends JpaRepository<SpaceUser, UUID> {
}
