package com.turkcell.authserver.services.concretes;

import com.turkcell.authserver.repositories.UserRepository;
import com.turkcell.authserver.services.abstracts.AuthService;
import com.turkcell.authserver.services.dtos.requests.LoginRequest;
import com.turkcell.authserver.services.dtos.requests.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    @Override
    public void register(RegisterRequest request) {

    }

    @Override
    public String login(LoginRequest request) {
        return null;
    }
}
