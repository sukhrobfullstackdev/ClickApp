package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.sudev.clickapp.entity.enums.TaskPermission;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CategoryUser {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    private Category categoryId;
    @ManyToOne
    private User userId;
    @Enumerated(EnumType.STRING)
    private TaskPermission taskPermission;
}
