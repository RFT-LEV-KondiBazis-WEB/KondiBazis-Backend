package hu.unideb.fitbase.web.token.generator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenGenerator {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration * 1000);
    }

    public String generateToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);
        Date d = new Date();
        return Jwts
                .builder()
                .setClaims(claims)
                .setExpiration(calculateExpirationDate(new Date(d.getTime())))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}