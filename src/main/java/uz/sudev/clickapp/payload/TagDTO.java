package uz.sudev.clickapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class TagDTO {
    private String name;
    private String color;
    private Long workspaceId;
}
