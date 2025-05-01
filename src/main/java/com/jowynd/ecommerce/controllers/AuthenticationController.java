package com.jowynd.ecommerce.controllers;

import com.jowynd.ecommerce.domain.user.User;
import com.jowynd.ecommerce.dto.user.UserAuthenticationDTO;
import com.jowynd.ecommerce.dto.user.UserCreateDTO;
import com.jowynd.ecommerce.repositories.UserRepository;
import com.jowynd.ecommerce.security.LoginResponseDTO;
import com.jowynd.ecommerce.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody @Valid UserAuthenticationDTO authenticationDTO) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.username(), authenticationDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok().body(new LoginResponseDTO(token));
    }

    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody @Valid UserCreateDTO dto) {

        if (userRepository.findByUsername(dto.username()) != null) {
            return ResponseEntity.badRequest().body("This user already exists");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        User newUser = new User(dto.username(), dto.email(), encryptedPassword, dto.userRole());

        userRepository.save(newUser);

        return ResponseEntity.ok().body("User created!");
    }
}
