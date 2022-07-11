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
public class CheckListItem extends AbstractUUIDEntity {
    private String name;
    @ManyToOne
    private CheckList checkList;
    private boolean resolved;
    @ManyToOne
    private User assignedUser;
}
