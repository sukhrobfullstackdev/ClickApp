package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class WorkspaceUser {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne(optional = false)
    private Workspace workspaceId;
    @ManyToOne(optional = false)
    private User userId;
    @ManyToOne
    private WorkspaceRole workspaceRoleId;
    @Column(nullable = false)
    private Timestamp dateInvited;
    @Column(nullable = false)
    private Timestamp dateJoined;
}
