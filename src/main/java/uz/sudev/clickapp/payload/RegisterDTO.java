package uz.sudev.clickapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class RegisterDTO {
    @NotNull(message = "Please enter your first name!")
    @Size(min = 3)
    private String firstName;
    @NotNull(message = "Please enter your last name!")
    @Size(min = 5)
    private String lastName;
    @NotNull(message = "Please enter your active email!")
    //@Email
    private String email;
    @NotNull(message = "Please enter your password!")
    @Size(min = 8)
    private String password;
}