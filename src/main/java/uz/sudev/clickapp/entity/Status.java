package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.sudev.clickapp.entity.enums.Type;
import uz.sudev.clickapp.entity.template.AbstractUUIDEntity;

import javax.persistence.*;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Status extends AbstractUUIDEntity {
    @Column(nullable = false)
    private String name;
    @ManyToOne
    private Space space;
    @ManyToOne
    private Project project;
    @ManyToOne
    private Category category;
    @Column(nullable = false)
    private String color;
    @Enumerated(EnumType.STRING)
    private Type type;
}
