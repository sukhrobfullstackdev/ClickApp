package uz.sudev.clickapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sudev.clickapp.entity.Icon;

@Repository
public interface IconRepository extends JpaRepository<Icon,Long> {
}
