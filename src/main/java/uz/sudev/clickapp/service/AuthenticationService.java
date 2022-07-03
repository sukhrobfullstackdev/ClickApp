package uz.sudev.clickapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.sudev.clickapp.entity.User;
import uz.sudev.clickapp.entity.enums.SystemRoleName;
import uz.sudev.clickapp.payload.LoginDTO;
import uz.sudev.clickapp.payload.Message;
import uz.sudev.clickapp.payload.RegisterDTO;
import uz.sudev.clickapp.repository.UserRepository;
import uz.sudev.clickapp.security.JWTProvider;

import java.util.Optional;
import java.util.Random;

@Service
public class AuthenticationService implements UserDetailsService {
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;
    final JavaMailSender javaMailSender;
    final AuthenticationManager authenticationManager;
    final JWTProvider jwtProvider;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JavaMailSender javaMailSender, AuthenticationManager authenticationManager, JWTProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public ResponseEntity<Message> register(RegisterDTO registerDTO) {
        if (!userRepository.existsByEmail(registerDTO.getEmail())) {
            String code = String.valueOf(new Random().nextInt(9999));
            userRepository.save(new User(registerDTO.getFirstName(), registerDTO.getLastName(), registerDTO.getEmail(), passwordEncoder.encode(registerDTO.getPassword()), SystemRoleName.SYSTEM_ROLE_USER, code));
            Boolean sentEmil = sendEmail(registerDTO.getEmail(), code);
            if (sentEmil) {
                return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "A message has sent to your email , please verify your email by entering the code that was sent to your email"));
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new Message(false, "An error has occurred while sending a message to your email, please try with another one!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Message(false, "This email is already in use!"));
        }
    }

    public Boolean sendEmail(String sendingEmail, String emailCode) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("sukhrobdev83@gmail.com");
            message.setTo(sendingEmail);
            message.setSubject("Email account confirmation message!");
            message.setText("<a href='http://localhost:8080/authentication/confirmEmail?emailCode=" + emailCode + "&sendingEmail=" + sendingEmail + "'>Confirm your email!</a>");
            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ResponseEntity<Message> confirmEmail(String emailCode, String sendingEmail) {
        Optional<User> optionalUser = userRepository.findByEmailAndEmailCode(sendingEmail, emailCode);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEnabled(true);
            user.setEmailCode(null);
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body(new Message(true, "Your account was confirmed!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "Your account was not confirmed!"));
        }
    }

    public ResponseEntity<Message> login(LoginDTO loginDTO) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
            User user = (User) authenticate.getPrincipal();
            String token = jwtProvider.generateToken(user.getUsername());
            return ResponseEntity.status(HttpStatus.OK).body(new Message(true, "You have successfully logged in!", token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "Password or login is incorrect!"));
        }
    }
}