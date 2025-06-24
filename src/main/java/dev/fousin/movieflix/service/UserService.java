package dev.fousin.movieflix.service;

import dev.fousin.movieflix.controller.request.UserRequest;
import dev.fousin.movieflix.entity.User;
import dev.fousin.movieflix.mapper.UserMapper;
import dev.fousin.movieflix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public User save(User user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return repository.save(user);
    }
}
