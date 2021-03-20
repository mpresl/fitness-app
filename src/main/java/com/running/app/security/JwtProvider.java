package com.running.app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import static io.jsonwebtoken.Jwts.parser;


@Service
public class JwtProvider {

  private final String jwtkey = "MikeRunning";

  public String generateToken(Authentication authentication) {
    org.springframework.security.core.userdetails.User principal =
        (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
    return Jwts.builder()
        .setSubject(principal.getUsername())
        .signWith(SignatureAlgorithm.HS256, jwtkey)
        .compact();
  }

  public boolean validateToken(String jwt) {
    parser().setSigningKey(jwtkey).parseClaimsJws(jwt);
    return true;
  }

  public String getUsernameFromJwt(String token) {
    Claims claims = parser()
        .setSigningKey(jwtkey)
        .parseClaimsJws(token)
        .getBody();

    return claims.getSubject();
  }

}
