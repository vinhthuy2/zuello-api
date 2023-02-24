package com.zulhke.zuello.auth;

import com.zulhke.zuello.config.JWTService;
import com.zulhke.zuello.models.user.Role;
import com.zulhke.zuello.models.user.User;
import com.zulhke.zuello.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JWTService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    var user = User
        .builder()
        .firstName(request.getFirstname())
        .lastName(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();

    var userDb = repository
        .findByEmail(request.getEmail())
        .orElse(null);

    // TODO: add Exception interceptor
    if (userDb != null) {
      throw new SecurityException("Existing user found");
    }

    repository.save(user);

    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse
        .builder()
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    var user = repository
        .findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse
        .builder()
        .token(jwtToken)
        .build();
  }
}
