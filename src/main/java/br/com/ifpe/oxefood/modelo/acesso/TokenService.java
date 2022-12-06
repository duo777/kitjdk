package br.com.ifpe.oxefood.modelo.acesso;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication) {

	User usuario = (User) authentication.getPrincipal();

	Date now = new Date();
	Date exp = new Date(now.getTime() + Long.parseLong(expiration));

	return Jwts.builder().setIssuer("MinhaAplicacao").setSubject(usuario.getId().toString()).setIssuedAt(new Date())
		.setExpiration(exp).signWith(SignatureAlgorithm.HS256, secret).compact();
    }
}
