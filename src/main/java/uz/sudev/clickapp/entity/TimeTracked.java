package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TimeTracked {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    private Task taskId;
    private Timestamp startedAt;
    private Timestamp stoppedAt;
}
