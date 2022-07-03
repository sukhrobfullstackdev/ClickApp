package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.sudev.clickapp.entity.template.AbstractLongEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Workspace extends AbstractLongEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String color;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User ownerId;
    @Column(nullable = false)
    private String initialLetter;
    @ManyToOne(fetch = FetchType.LAZY)
    private Attachment avatarId;
}
