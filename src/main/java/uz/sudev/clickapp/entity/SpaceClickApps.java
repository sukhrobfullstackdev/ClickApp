package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SpaceClickApps {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    private Space spaceId;
    @ManyToOne
    private ClickApps clickAppsId;
}
