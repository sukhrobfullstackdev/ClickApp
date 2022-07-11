package uz.sudev.clickapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class CheckListItemDTO {
    private String name;
    private Long checkListId;
    private boolean resolved;
    private UUID assignedUserId;
}
