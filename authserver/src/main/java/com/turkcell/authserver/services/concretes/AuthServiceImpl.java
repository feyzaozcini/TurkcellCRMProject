package com.turkcell.authserver.services.concretes;
import com.turkcell.authserver.core.utils.types.BusinessException;
import com.turkcell.authserver.entities.User;
import com.turkcell.authserver.services.abstracts.AuthService;
import com.turkcell.authserver.services.abstracts.UserService;
import com.turkcell.authserver.services.dtos.requests.LoginRequest;
import com.turkcell.authserver.services.dtos.requests.RegisterRequest;
import com.turkcell.authserver.services.mappers.UserMapper;
import com.turkcell.tcellfinalcore.services.BaseJwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final BaseJwtService jwtService;

    @Override
    public void register(RegisterRequest request) {
        if (userService.existsByEmail(request.getEmail())) {
            throw new BusinessException("Bu e-posta adresi zaten mevcut.");
        }

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
            throw new BusinessException("Kullanici adi ya da sifre yanlis!");
        }
        return jwtService.generateTokenWithClaims(userService.loadUserByUsername(request.getEmail()));

    }


}
