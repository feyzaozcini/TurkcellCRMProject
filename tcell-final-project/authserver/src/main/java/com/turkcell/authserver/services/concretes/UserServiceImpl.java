package com.turkcell.authserver.services.concretes;

import com.turkcell.authserver.clients.CustomerServiceClient;
import com.turkcell.authserver.entities.User;
import com.turkcell.authserver.repositories.UserRepository;
import com.turkcell.authserver.services.abstracts.UserService;
import com.turkcell.authserver.services.dtos.responses.CustomerAddressGet;
import com.turkcell.authserver.services.dtos.responses.CustomerContactGet;
import com.turkcell.authserver.services.dtos.responses.CustomerGet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CustomerServiceClient customerServiceClient;
    @Getter
    private String token;
    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new BadCredentialsException(""));
    }
    public List<CustomerGet> getCustomers(String token){
        this.token = token;
        return customerServiceClient.getCustomers();
    }


    public List<CustomerAddressGet> getCustomerAddressesByCustomerId( int customerId){
        return customerServiceClient.getCustomerAddressesByCustomerId(customerId);
    }

    public List<CustomerContactGet> getCustomerContactsByCustomerId(int customerId){
        return customerServiceClient.getCustomerContactsByCustomerId(customerId);
    }
    public List<CustomerContactGet> getCustomerContactsByCustomerId(String token, int customerId){
        this.token = token;
        return customerServiceClient.getCustomerContactsByCustomerId(customerId);
    }
}
//'http://localhost:8084/api/v1/customer/adresses?customerId=2'