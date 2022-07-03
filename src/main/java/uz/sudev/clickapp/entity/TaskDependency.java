package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.sudev.clickapp.entity.enums.DependencyType;
import uz.sudev.clickapp.entity.template.AbstractUUIDEntity;

import javax.persistence.*;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TaskDependency extends AbstractUUIDEntity {
    @ManyToOne
    private Task taskId;
    @ManyToOne
    private Task dependencyTaskId;
    @Enumerated(EnumType.STRING)
    private DependencyType dependencyType;
}
