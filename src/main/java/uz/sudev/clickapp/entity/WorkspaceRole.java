package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.sudev.clickapp.entity.enums.WorkspaceRoleName;
import uz.sudev.clickapp.entity.template.AbstractUUIDEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"workspace_id","name"}))
public class WorkspaceRole extends AbstractUUIDEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Workspace workspace;
    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private WorkspaceRoleName extendsRole;
}
