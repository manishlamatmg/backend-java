package com.elderlyCare.api.customer.controller;

import com.elderlyCare.api.base.HttpResponsesMessage;
import com.elderlyCare.api.customer.dto.CustomerDTO;
import com.elderlyCare.api.customer.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public HttpResponsesMessage saveCustomerInfo(@RequestBody CustomerDTO customerDTO){
       return customerService.saveCustomerInformation(customerDTO);
    }


    @DeleteMapping("/delete{id}")
    public HttpResponsesMessage deleteCustomer(@PathVariable("id") String id){
        return customerService.deleteCustomerInformation(id);
    }


    @GetMapping("/customer")
    public List<CustomerDTO> getAll(){
        return customerService.getALLInformation();
    }


}
