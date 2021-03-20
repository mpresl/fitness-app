package com.running.app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

import static io.jsonwebtoken.Jwts.parser;


@Service
public class JwtProvider {

  @Value("${jwt.token.key}")
  private String jwtkey;

  @Value("${jwt.expiration.time}")
  private Long jwtExpirationInMillis;

  public String generateToken(Authentication authentication) {
    org.springframework.security.core.userdetails.User principal =
        (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
    return Jwts.builder()
        .setSubject(principal.getUsername())
        .setIssuedAt(Date.from(Instant.now()))
        .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
        .signWith(SignatureAlgorithm.HS256, jwtkey)
        .compact();
  }

  public String generateTokenWithUserName(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(Date.from(Instant.now()))
        .signWith(SignatureAlgorithm.HS256, jwtkey)
        .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
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

  public Long getJwtExpirationInMillis() {
    return jwtExpirationInMillis;
  }

}
