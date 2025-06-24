package dev.fousin.movieflix.controller;

import dev.fousin.movieflix.config.TokenService;
import dev.fousin.movieflix.controller.request.LoginRequest;
import dev.fousin.movieflix.controller.request.UserRequest;
import dev.fousin.movieflix.controller.response.LoginResponse;
import dev.fousin.movieflix.controller.response.UserResponse;
import dev.fousin.movieflix.entity.User;
import dev.fousin.movieflix.exceptions.UsernameOrPasswordInvalidException;
import dev.fousin.movieflix.mapper.UserMapper;
import dev.fousin.movieflix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movieflix/auth")
public class AuthController {
    @Autowired
    private UserService service;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest request) {
        User user = service.save(UserMapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toResponse(user));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
            Authentication authenticate = authenticationManager.authenticate(userAndPass);
            User user = (User) authenticate.getPrincipal();
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (BadCredentialsException e) {
            throw new UsernameOrPasswordInvalidException("Usuário ou senha inválidos");
        }
    }
}
