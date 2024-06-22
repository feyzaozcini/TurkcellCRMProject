package com.turkcell.authserver.services.abstracts;
import com.turkcell.authserver.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
public interface UserService extends UserDetailsService {
    void add(User user);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
