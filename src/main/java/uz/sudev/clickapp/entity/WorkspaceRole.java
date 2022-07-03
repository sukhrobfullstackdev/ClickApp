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
public class WorkspaceRole {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne(optional = false)
    private Workspace workspaceId;
    @Column(nullable = false)
    private String name;
    private String extendsRole; // may change
}
