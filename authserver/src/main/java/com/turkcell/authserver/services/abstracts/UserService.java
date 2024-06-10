package com.turkcell.authserver.services.abstracts;

import com.turkcell.authserver.entities.User;
import com.turkcell.authserver.services.dtos.requests.CustomerUpdateRequest;
import com.turkcell.authserver.services.dtos.requests.SearchRequest;
import com.turkcell.authserver.services.dtos.responses.CustomerAddressGet;
import com.turkcell.authserver.services.dtos.responses.CustomerContactGet;
import com.turkcell.authserver.services.dtos.responses.CustomerGet;
import com.turkcell.authserver.services.dtos.responses.SearchResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {
    void add(User user);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    String getToken();

}
