package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.sudev.clickapp.entity.enums.AccessType;
import uz.sudev.clickapp.entity.template.AbstractUUIDEntity;

import javax.persistence.*;
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
    private Workspace workspace;
    @Column(nullable = false)
    private String initialLetter;
    @ManyToOne(optional = false)
    private Icon icon;
    @ManyToOne(optional = false)
    private Attachment avatar;
    @ManyToOne(optional = false)
    private User owner;
    @Enumerated(EnumType.STRING)
    private AccessType accessType;
    @PrePersist
    @PreUpdate
    public void setInitialLetter() {
        this.initialLetter = name.substring(0, 1);
    }
    public String getAccessType() {
        return accessType.name();
    }

}
