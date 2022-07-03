package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.sudev.clickapp.entity.template.AbstractUUIDEntity;

import javax.persistence.*;
import java.util.UUID;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TaskHistory extends AbstractUUIDEntity {
    @ManyToOne
    private Task taskId;
    @Column(nullable = false)
    private String changeFieldName;
    @ManyToOne
    private TaskHistory before;
    @ManyToOne
    private TaskHistory after;
    @Column(nullable = false, columnDefinition = "text")
    private String data;
}
