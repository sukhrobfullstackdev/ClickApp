package uz.sudev.clickapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import uz.sudev.clickapp.entity.enums.WorkspaceRoleName;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class MemberDTO {
    private UUID memberId;
    private UUID roleId;
    private String methodType;
}
