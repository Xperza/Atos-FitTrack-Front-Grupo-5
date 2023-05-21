package com.atos.fittrack.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.atos.fittrack.entities.Usuario;
import com.atos.fittrack.services.UsuarioService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@Service
public class JwtTokenUtil {

	@Value("${jwt.secret}")
    private String SECRET_KEY;
	
	@Autowired
	private UsuarioService servicio;
	
	private Usuario user;

    public String generateToken(UserDetails userDetails) {
        this.user = servicio.findOneByEmail(userDetails.getUsername()).get();
        Map<String, Object> claims = new HashMap<>(); 
        claims.put("id_usuario", this.user.getId());
        claims.put("nombre", this.user.getNombre());
        claims.put("apellidos", this.user.getApellidos());
        return Jwts.builder()
        		.setSubject(userDetails.getUsername())
        		.setExpiration(new Date(System.currentTimeMillis() + 604800000))
        		.addClaims(claims)
        		.signWith( SignatureAlgorithm.HS256, SECRET_KEY)
        		.compact();

    }

    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }
}