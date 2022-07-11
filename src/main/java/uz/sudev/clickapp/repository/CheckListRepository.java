package uz.sudev.clickapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sudev.clickapp.entity.CheckList;

import java.util.UUID;

@Repository
public interface CheckListRepository extends JpaRepository<CheckList,Long> {
    boolean existsByNameAndTaskId(String name, UUID task_id);
    boolean existsByNameAndTaskIdAndIdNot(String name, UUID task_id, Long id);
}
