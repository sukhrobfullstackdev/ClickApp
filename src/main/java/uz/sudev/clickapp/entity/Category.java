package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.sudev.clickapp.entity.enums.AccessType;
import uz.sudev.clickapp.entity.template.AbstractLongEntity;

import javax.persistence.*;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Category extends AbstractLongEntity {
    @Column(nullable = false)
    private String name;
    @ManyToOne(optional = false)
    private Project projectId;
    @Enumerated(EnumType.STRING)
    private AccessType accessType;
    private boolean archived;
    @Column(nullable = false)
    private String color;
}
