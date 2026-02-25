package com.javaweb.api.web;

import com.javaweb.model.request.CustomerCreateRequest;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactAPI {
    @Autowired
    private CustomerService customerService;
    @PostMapping
    public ResponseDTO createContact(@RequestBody CustomerCreateRequest request){
        return customerService.createFromContact(request);
    }
}
