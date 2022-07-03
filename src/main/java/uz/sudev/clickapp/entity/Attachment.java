package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.sudev.clickapp.entity.template.AbstractUUIDEntity;

import javax.persistence.Entity;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Attachment extends AbstractUUIDEntity {
    private String name;
    private String originalName;
    private Long size;
    private String contentType;
}
