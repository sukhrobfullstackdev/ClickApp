package uz.sudev.clickapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import uz.sudev.clickapp.entity.enums.AccessType;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class ProjectDTO {
    private String name;
    private UUID spaceId;
    private AccessType accessType;
    private boolean archived;
    private String color;
}
