package com.running.app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

import static io.jsonwebtoken.Jwts.parser;


@Service
public class JwtProvider {

  private final String jwtkey = "MikeRunning";

  public String generateToken(Authentication authentication) {
    org.springframework.security.core.userdetails.User principal =
        (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
    return Jwts.builder()
        .setSubject(principal.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
        .signWith(SignatureAlgorithm.HS256, jwtkey)
        .compact();
  }

  public boolean validateToken(String jwt) {
    parser().setSigningKey(jwtkey).parseClaimsJws(jwt);
    return !isTokenExpired(jwt);
  }

  public String getUsernameFromJwt(String token) {
    Claims claims = parser()
        .setSigningKey(jwtkey)
        .parseClaimsJws(token)
        .getBody();

    return claims.getSubject();
  }
  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(jwtkey).parseClaimsJws(token).getBody();
  }

  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

}
