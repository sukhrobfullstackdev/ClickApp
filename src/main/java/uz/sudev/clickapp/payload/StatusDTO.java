package uz.sudev.clickapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import uz.sudev.clickapp.entity.enums.Type;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class StatusDTO {
    private String name;
    private UUID spaceId;
    private Long projectId;
    private Long categoryId;
    private String color;
    private Type type;
}
