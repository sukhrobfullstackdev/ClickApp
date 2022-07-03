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
public class ProjectUser {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne(optional = false)
    private Project projectId;
    @ManyToOne(optional = false)
    private User userId;
    @Enumerated(EnumType.STRING)
    private TaskPermission taskPermission;
}
