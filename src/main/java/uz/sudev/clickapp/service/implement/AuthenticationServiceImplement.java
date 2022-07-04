package uz.sudev.clickapp.service.implement;

import org.springframework.http.ResponseEntity;
import uz.sudev.clickapp.payload.LoginDTO;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.RegisterDTO;

public interface AuthenticationServiceImplement {
    ResponseEntity<Message> register(RegisterDTO registerDTO);
    Boolean sendEmail(String sendingEmail, String emailCode);
    ResponseEntity<Message> confirmEmail(String emailCode, String sendingEmail);
    ResponseEntity<Message> login(LoginDTO loginDTO);
}
