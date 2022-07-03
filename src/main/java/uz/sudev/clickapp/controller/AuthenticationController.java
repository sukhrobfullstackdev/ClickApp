package uz.sudev.clickapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sudev.clickapp.payload.LoginDTO;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.RegisterDTO;
import uz.sudev.clickapp.service.AuthenticationService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationController {
    final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Message> register(@Valid @RequestBody RegisterDTO registerDTO) {
        return authenticationService.register(registerDTO);
    }
    @GetMapping("/confirmEmail")
    public ResponseEntity<Message> confirmEmail(@RequestParam String emailCode, @RequestParam String sendingEmail) {
        return authenticationService.confirmEmail(emailCode, sendingEmail);
    }
    @PostMapping("/login")
    public ResponseEntity<Message> login(@RequestBody LoginDTO loginDTO) {
        return authenticationService.login(loginDTO);
    }
}
