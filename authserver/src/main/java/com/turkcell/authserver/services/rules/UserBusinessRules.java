package com.turkcell.authserver.services.rules;

import com.turkcell.authserver.core.utils.types.BusinessException;
import com.turkcell.authserver.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBusinessRules {
    private final UserRepository userRepository;

    public void checkIfUserAlreadyExists(String email) {
        if (userRepository.existsByEmail(email)){
            throw new BusinessException("This email address already exists.");
        }
    }


    public void checkIfUserNotExists(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new BusinessException("User not found!");
        }
    }
}
