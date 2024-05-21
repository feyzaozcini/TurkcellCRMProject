package com.turkcell.authserver.services.concretes;
import com.turkcell.authserver.clients.CustomerServiceClient;
import com.turkcell.authserver.entities.User;
import com.turkcell.authserver.services.abstracts.AuthService;
import com.turkcell.authserver.services.abstracts.UserService;
import com.turkcell.authserver.services.dtos.requests.LoginRequest;
import com.turkcell.authserver.services.dtos.requests.RegisterRequest;
import com.turkcell.authserver.services.dtos.responses.CustomerGet;
import com.turkcell.authserver.services.mappers.UserMapper;
import com.turkcell.tcellfinalcore.services.BaseJwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final BaseJwtService jwtService;
    private final CustomerServiceClient customerServiceClient;
    private String token;
    @Override
    public void register(RegisterRequest request) {
        User user = UserMapper.INSTANCE.userFromRegisterRequest(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.add(user);
    }

    @Override
    public String login(LoginRequest request) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        }
        catch(AuthenticationException exception){
            throw new RuntimeException("Kullanici adi ya da sifre yanlis");
        }

        token = jwtService.generateTokenWithClaims(userService.loadUserByUsername(request.getEmail()));
        return token;
    }

    public List<CustomerGet> getCustomers(){
        return customerServiceClient.getCustomers();
    }

    public String getToken(){
        return token;
    }
}
