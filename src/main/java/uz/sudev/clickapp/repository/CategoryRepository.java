package uz.sudev.clickapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sudev.clickapp.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
