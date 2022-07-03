package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TaskHistory {
    @Id
    @GeneratedValue
    private UUID id;
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
