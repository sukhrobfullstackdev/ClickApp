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
public class Icon extends AbstractLongEntity {
    @ManyToOne
    private Attachment attachment;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String initialLetter;
    @ManyToOne
    private Attachment icon; // may change
}
