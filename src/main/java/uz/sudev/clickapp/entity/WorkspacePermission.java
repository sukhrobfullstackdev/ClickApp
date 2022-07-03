package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.sudev.clickapp.entity.enums.Permission;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class WorkspacePermission {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    private WorkspaceRole workspaceRoleId;
    @Enumerated(EnumType.STRING)
    private Permission permission;
}
