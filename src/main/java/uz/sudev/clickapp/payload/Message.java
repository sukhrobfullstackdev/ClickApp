package uz.sudev.clickapp.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@JsonInclude(JsonInclude.Include.NON_NULL) // null bo'lgan fieldlarni bervormidi client larga
public class Message {
    private boolean success;
    private String message;
    private String token;

    public Message(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}