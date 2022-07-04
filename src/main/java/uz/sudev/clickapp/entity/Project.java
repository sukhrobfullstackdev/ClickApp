package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.sudev.clickapp.entity.enums.AccessType;
import uz.sudev.clickapp.entity.template.AbstractLongEntity;
import uz.sudev.clickapp.payload.ProjectDTO;

import javax.persistence.*;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Project extends AbstractLongEntity {
    @Column(nullable = false)
    private String name;
    @ManyToOne
    private Space space;
    @Enumerated(EnumType.STRING)
    private AccessType accessType;
    private boolean archived;
    @Column(nullable = false)
    private String color;
}
