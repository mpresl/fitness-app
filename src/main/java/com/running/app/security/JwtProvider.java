package com.running.app.security;

import com.running.app.exceptions.MikeRunningException;
import com.running.app.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

@Service
public class JwtProvider {

  private KeyStore keyStore;

  @PostConstruct
  public void init() {
    try {
      keyStore = KeyStore.getInstance("JKS");
      InputStream resourceAsStream = getClass().getResourceAsStream("/springblog.jks");
      keyStore.load(resourceAsStream, "secret".toCharArray());
    } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException exception) {
      throw new MikeRunningException("Exception occured while loading keystore");
    }
  }

  public String generateToken(Authentication authentication) {
    org.springframework.security.core.userdetails.User principal =
        (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
    return Jwts.builder()
        .setSubject(principal.getUsername())
        .signWith(SignatureAlgorithm.HS256, getPrivateKey())
        .compact();
  }

  public PrivateKey getPrivateKey() {
    try {
      return (PrivateKey) keyStore.getKey("springblog", "secret".toCharArray());
    } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException exception) {
      throw new MikeRunningException("Exception occured while retrieving public key from keystore");
    }
  }
}
