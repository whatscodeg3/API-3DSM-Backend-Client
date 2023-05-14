package com.br.WhatsCodeClientMicroservice.controller;


import com.br.WhatsCodeClientMicroservice.dto.LoginDto;
import com.br.WhatsCodeClientMicroservice.models.Employee;
import com.br.WhatsCodeClientMicroservice.models.User;
import com.br.WhatsCodeClientMicroservice.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.login(),
                        loginDto.password());

        Authentication authenticate = this.authenticationManager
                .authenticate(usernamePasswordAuthenticationToken);

        var employee = (Employee) authenticate.getPrincipal();

        return tokenService.gerarToken(employee);

    }
    
    @GetMapping("/check")
    public String check() {
        return "OK";
    }
}
