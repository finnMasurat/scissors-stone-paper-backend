package test.scissorsstonepaper.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import test.scissorsstonepaper.model.Player;
import test.scissorsstonepaper.service.PlayerService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {
    private String SECRET_KEY = "secret";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Long extractPlayerId(String token) {
        Claims claims = extractAllClaims(token);
        System.out.println("PLAAAAAYER_ID: " + claims.get("playerId"));
        return ((Number)claims.get("playerId")).longValue();
    }
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(Player player) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, player.getEmail(), player.getId());
    }
    private String createToken(Map<String, Object> claims, String subject, Long playerId) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .claim("playerId", playerId)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String getEmailFromHeader(String header) {
        return this.extractUsername(header.substring(7));
    }

    public Long getPlayerIdFromHeader(String header) {
        return this.extractPlayerId(header.substring(7));
    }
}
