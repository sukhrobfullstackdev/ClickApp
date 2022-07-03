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
public class Space extends AbstractUUIDEntity {
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String color;
    @ManyToOne(optional = false)
    private Workspace workspaceId;
    @Column(nullable = false)
    private String initialLetter;
    @ManyToOne(optional = false)
    private Icon iconId;
    @ManyToOne(optional = false)
    private Attachment avatarId;
    @ManyToOne(optional = false)
    private User ownerId;
    @Enumerated(EnumType.STRING)
    private AccessType accessType;

    public String getAccessType() {
        return accessType.name();
    }
}
