package uz.sudev.clickapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sudev.clickapp.entity.Priority;

import java.util.UUID;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, UUID> {
}
