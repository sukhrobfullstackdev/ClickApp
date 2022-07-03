package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.sudev.clickapp.entity.enums.WorkspacePermissionName;
import uz.sudev.clickapp.entity.template.AbstractUUIDEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class WorkspacePermission extends AbstractUUIDEntity {
    @ManyToOne
    private WorkspaceRole workspaceRoleId;
    @Enumerated(EnumType.STRING)
    private WorkspacePermissionName permission;
}
