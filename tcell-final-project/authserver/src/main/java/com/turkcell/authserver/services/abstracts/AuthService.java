package com.turkcell.authserver.services.abstracts;

import com.turkcell.authserver.services.dtos.requests.LoginRequest;
import com.turkcell.authserver.services.dtos.requests.RegisterRequest;
import com.turkcell.authserver.services.dtos.responses.CustomerGet;

import java.util.List;

public interface AuthService {
    void register(RegisterRequest request);
    String login(LoginRequest request);

}
