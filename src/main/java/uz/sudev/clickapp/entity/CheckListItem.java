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
public class CheckListItem {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    @ManyToOne
    private CheckList checkListId;
    private boolean resolved;
    @ManyToOne
    private User assignedUserId;
}
