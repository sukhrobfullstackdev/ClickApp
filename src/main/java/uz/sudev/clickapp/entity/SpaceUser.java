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
public class SpaceUser {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne(optional = false)
    private Space spaceId;
    @ManyToOne(optional = false)
    private User memberId;
}
