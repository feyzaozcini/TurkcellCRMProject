package com.turkcell.authserver.controllers;
import com.turkcell.authserver.services.abstracts.UserService;
import com.turkcell.authserver.services.dtos.requests.CustomerUpdateRequest;
import com.turkcell.authserver.services.dtos.requests.SearchRequest;
import com.turkcell.authserver.services.dtos.responses.CustomerAddressGet;
import com.turkcell.authserver.services.dtos.responses.CustomerContactGet;
import com.turkcell.authserver.services.dtos.responses.CustomerGet;
import com.turkcell.authserver.services.dtos.responses.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/getCustomers")
    public List<CustomerGet> getCustomers(@RequestParam String token){
        return userService.getCustomers(token);
    }
    @GetMapping("/getCustomerAddresses")
    public List<CustomerAddressGet> getCustomerAddressesByCustomerId(@RequestParam String token,@RequestParam int customerId){
        return userService.getCustomerAddressesByCustomerId(token,customerId);
    }
    @GetMapping("/getCustomerContacts")
    public List<CustomerContactGet> getCustomerContactsByCustomerId(@RequestParam String token, @RequestParam int customerId){
        return userService.getCustomerContactsByCustomerId(token,customerId);
    }
    @PostMapping("/searchCustomer")
    public List<SearchResponse> searchCustomer(@RequestBody SearchRequest request){
        return userService.searchCustomer(request);
    }
    @PutMapping("/updateCustomer")
    public void updateCustomer(@RequestParam String token, @RequestBody CustomerUpdateRequest request){
        userService.updateCustomer(token,request);
    }

}
