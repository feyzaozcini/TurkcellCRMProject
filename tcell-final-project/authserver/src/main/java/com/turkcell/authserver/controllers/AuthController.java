package com.turkcell.authserver.controllers;

import com.turkcell.authserver.clients.CustomerServiceClient;
import com.turkcell.authserver.services.abstracts.AuthService;
import com.turkcell.authserver.services.dtos.requests.LoginRequest;
import com.turkcell.authserver.services.dtos.requests.RegisterRequest;
import com.turkcell.authserver.services.dtos.responses.CustomerGet;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final CustomerServiceClient customerServiceClient;
    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request){
        authService.register(request);
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        return authService.login(request);
    }

//    @GetMapping("/customers")
//    public List<CustomerGet> getCustomers(){
//        return authService.getCustomers();
//    }
}
