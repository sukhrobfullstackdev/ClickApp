package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.sudev.clickapp.entity.template.AbstractUUIDEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TaskAttachment extends AbstractUUIDEntity {
    @ManyToOne
    private Task taskId;
    @ManyToOne
    private Attachment attachmentId;
    private boolean pinCoverImage;
}
