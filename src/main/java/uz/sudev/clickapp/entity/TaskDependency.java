package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.sudev.clickapp.entity.enums.DependencyType;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TaskDependency {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    private Task taskId;
    @ManyToOne
    private Task dependencyTaskId;
    @Enumerated(EnumType.STRING)
    private DependencyType dependencyType;
}
