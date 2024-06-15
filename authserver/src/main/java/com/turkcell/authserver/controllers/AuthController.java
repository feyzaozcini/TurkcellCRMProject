package com.turkcell.authserver.controllers;
import com.turkcell.authserver.core.utils.types.BusinessException;
import com.turkcell.authserver.services.abstracts.AuthService;
import com.turkcell.authserver.services.dtos.requests.LoginRequest;
import com.turkcell.authserver.services.dtos.requests.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        try {
            authService.register(request);
            return ResponseEntity.ok("Kayıt başarılı.");
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest request){
        return authService.login(request);
    }
}
