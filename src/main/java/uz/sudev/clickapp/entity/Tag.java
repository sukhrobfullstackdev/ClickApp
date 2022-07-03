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
public class Tag {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String color;
    @ManyToOne
    private Workspace workspaceId;
}
