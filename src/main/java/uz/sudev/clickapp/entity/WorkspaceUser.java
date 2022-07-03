package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.sudev.clickapp.entity.template.AbstractUUIDEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class WorkspaceUser extends AbstractUUIDEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Workspace workspaceId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User userId;
    @ManyToOne
    private WorkspaceRole workspaceRoleId;
    @Column(nullable = false)
    private Timestamp dateInvited;
    private Timestamp dateJoined;
}
