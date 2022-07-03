package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.sudev.clickapp.entity.enums.AccessType;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Project {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    private Space spaceId;
    @Enumerated(EnumType.STRING)
    private AccessType accessType;
    private boolean archived;
    @Column(nullable = false)
    private String color;
}
