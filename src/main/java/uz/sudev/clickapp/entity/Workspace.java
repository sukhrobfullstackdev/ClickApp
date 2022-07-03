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
public class Workspace extends AbstractUUIDEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String color;
    @ManyToOne(optional = false)
    private User ownerId;
    @Column(nullable = false)
    private String initialLetter;
    @ManyToOne
    private Attachment avatarId;
}
