package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.sudev.clickapp.entity.enums.Type;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Status {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    private Space spaceId;
    @ManyToOne
    private Project projectId;
    @ManyToOne
    private Category categoryId;
    @Column(nullable = false)
    private String color;
    @Enumerated(EnumType.STRING)
    private Type type;
}
