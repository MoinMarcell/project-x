package com.github.moinmarcell.projectxbackend.auth;

import com.github.moinmarcell.projectxbackend.exception.UserAlreadyExistException;
import com.github.moinmarcell.projectxbackend.model.FliprUser;
import com.github.moinmarcell.projectxbackend.model.Role;
import com.github.moinmarcell.projectxbackend.repository.FliprUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final FliprUserRepository fliprUserRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponse register(RegisterRequest request) {

        if (checkIfUsernameExists(request.getUsername())) {
            throw new UserAlreadyExistException("Username already exists");
        }

        if (checkIfEmailExists(request.getEmail())) {
            throw new UserAlreadyExistException("Email already exists");
        }

        var userToSave = FliprUser.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .createdAt(Instant.now().toString())
                .fliprs(new ArrayList<>())
                .role(Role.USER)
                .build();

        var savedUser = fliprUserRepository.save(userToSave);

        return RegisterResponse.builder()
                .username(savedUser.getUsername())
                .createdAt(savedUser.getCreatedAt())
                .successMessage("User successfully registered")
                .build();
    }

    private boolean checkIfUsernameExists(String username) {
        return fliprUserRepository.findByUsernameIgnoreCase(username).isPresent();
    }

    private boolean checkIfEmailExists(String email) {
        return fliprUserRepository.findByEmail(email).isPresent();
    }
}
