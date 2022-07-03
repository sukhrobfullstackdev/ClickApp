package uz.sudev.clickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Workspace {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String color;
    @ManyToOne(optional = false)
    private User ownerId;
    @Column(nullable = false)
    private String initialLetter;
    @ManyToOne
    private Attachment avatarId;
}
