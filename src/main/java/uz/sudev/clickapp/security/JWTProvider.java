package uz.sudev.clickapp.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

@Component
public class JWTProvider {
    long expireTime = 1000 * 60 * 60;
    Date expireDate = new Date(System.currentTimeMillis() + expireTime);
    String secretKey = "Shirin";

    public String generateToken(String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(expireDate).signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }

    public String getEmailFromToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e) {
            return null;
        }
    }
//    claim - bu obyekt berib yuborish uchun.
}
