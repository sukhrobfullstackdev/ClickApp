package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.sudev.clickapp.entity.template.AbstractUUIDEntity;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class WorkspaceUser extends AbstractUUIDEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Workspace workspace;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;
    @ManyToOne
    private WorkspaceRole workspaceRole;
    @Column(nullable = false)
    private Timestamp dateInvited;
    private Timestamp dateJoined;

    public WorkspaceUser(Workspace workspace, User user, WorkspaceRole workspaceRole, Timestamp dateInvited) {
        this.workspace = workspace;
        this.user = user;
        this.workspaceRole = workspaceRole;
        this.dateInvited = dateInvited;
    }
}
