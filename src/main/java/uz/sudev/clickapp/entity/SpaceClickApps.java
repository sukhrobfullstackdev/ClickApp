package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.sudev.clickapp.entity.template.AbstractUUIDEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SpaceClickApps extends AbstractUUIDEntity {
    @ManyToOne
    private Space spaceId;
    @ManyToOne
    private ClickApps clickAppsId;
}
