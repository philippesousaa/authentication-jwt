package com.authentication.authentication.controller.authetication;

import com.authentication.authentication.domain.users.UserAuthenticationDTO;
import com.authentication.authentication.domain.users.Users;
import com.authentication.authentication.domain.users.UsersLoginDTO;
import com.authentication.authentication.domain.users.UsersRegisterDTO;
import com.authentication.authentication.repository.users.UsersRepository;
import com.authentication.authentication.service.token.TokenService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@Slf4j
public class AuthenticationController {


    private final AuthenticationManager authenticationManager;
    private final UsersRepository usersRepository;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, UsersRepository usersRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.usersRepository = usersRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserAuthenticationDTO userAuthenticationDTO) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(userAuthenticationDTO.login(), userAuthenticationDTO.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        var token = tokenService.generateToken((Users) auth.getPrincipal());


        return ResponseEntity.ok(new UsersLoginDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UsersRegisterDTO usersRegisterDTO) {
        if (this.usersRepository.findByLogin(usersRegisterDTO.login()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(usersRegisterDTO.password());
        Users newUser = new Users(
                usersRegisterDTO.login(),
                encryptedPassword,
                usersRegisterDTO.role()
        );
        this.usersRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
