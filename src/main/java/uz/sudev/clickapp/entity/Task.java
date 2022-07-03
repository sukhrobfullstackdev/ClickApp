package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Task {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, columnDefinition = "text")
    private String description;
    @ManyToOne
    private Status statusId;
    @ManyToOne
    private Category categoryId;
    @ManyToOne
    private Priority priorityId;
    @ManyToOne
    private Task parentTaskId;
    private Timestamp startedDate;
    private boolean startTimeHas;
    private Timestamp dueDate;
    private boolean dueTimeHas;
    private long estimateTime;
    private Timestamp activatedDate;
}
