package uz.sudev.clickapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sudev.clickapp.entity.CheckListItem;

import java.util.UUID;

@Repository
public interface CheckListItemRepository extends JpaRepository<CheckListItem, UUID> {
    boolean existsByNameAndAssignedUserIdAndCheckListId(String name, UUID assignedUser_id, Long checkList_id);
    boolean existsByNameAndAssignedUserIdAndCheckListIdAndIdNot(String name, UUID assignedUser_id, Long checkList_id, UUID id);
}
