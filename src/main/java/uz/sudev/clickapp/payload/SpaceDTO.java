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
public class SpaceDTO {
    private String name;
    private String color;
    private Long workspaceId;
    private Long iconId;
    private UUID avatarId;
    private AccessType accessType;
}
