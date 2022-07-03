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
public class Icon {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    private Attachment attachmentId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String initialLetter;
    @ManyToOne
    private Attachment icon; // may change
}
