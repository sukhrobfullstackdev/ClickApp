package uz.sudev.clickapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class TaskDTO {
    private String name;
    private String description;
    private UUID statusId;
    private Long categoryId;
    private UUID priorityId;
    private UUID parentTaskId;
    private Timestamp startedDate;
    private boolean startTimeHas;
    private Timestamp dueDate;
    private boolean dueTimeHas;
    private Timestamp activatedDate;
}
