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
public class TaskAttachmentDTO {
    private UUID taskId;
    private UUID attachmentId;
    private boolean pinCoverImage;
}
