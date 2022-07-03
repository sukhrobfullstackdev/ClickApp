package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TaskAttachment {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    private Task taskId;
    @ManyToOne
    private Attachment attachmentId;
    private boolean pinCoverImage;
}
