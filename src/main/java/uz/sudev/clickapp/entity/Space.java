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
public class Space {
    @Id
    @GeneratedValue
    private UUID id;
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
