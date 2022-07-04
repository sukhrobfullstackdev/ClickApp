package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.sudev.clickapp.entity.enums.TaskPermission;
import uz.sudev.clickapp.entity.template.AbstractUUIDEntity;

import javax.persistence.*;
import java.util.UUID;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ProjectUser extends AbstractUUIDEntity {
    @ManyToOne(optional = false)
    private Project project;
    @ManyToOne(optional = false)
    private User user;
    @Enumerated(EnumType.STRING)
    private TaskPermission taskPermission;
}
