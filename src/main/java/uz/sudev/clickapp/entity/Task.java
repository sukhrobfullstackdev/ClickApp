package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.sudev.clickapp.entity.template.AbstractUUIDEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Task extends AbstractUUIDEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, columnDefinition = "text")
    private String description;
    @ManyToOne
    private Status status;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Priority priority;
    @ManyToOne
    private Task parentTask;
    private Timestamp startedDate;
    private boolean startTimeHas;
    private Timestamp dueDate;
    private boolean dueTimeHas;
    private long estimateTime;
    private Timestamp activatedDate;
}
