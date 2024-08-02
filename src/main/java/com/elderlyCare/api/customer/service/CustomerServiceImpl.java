package com.elderlyCare.api.customer.service;

import com.elderlyCare.api.base.HttpResponsesMessage;
import com.elderlyCare.api.customer.dto.CustomerDTO;
import com.elderlyCare.api.customer.entity.CustomerInfo;
import com.elderlyCare.api.customer.repo.CustomerInfoRepository;
import com.elderlyCare.api.exception.ElderlyCareException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{


    private final CustomerInfoRepository customerInfoRepository;
    public CustomerServiceImpl(CustomerInfoRepository customerInfoRepository){
        this.customerInfoRepository = customerInfoRepository;
    }

    @Override
    public HttpResponsesMessage saveCustomerInformation(CustomerDTO customerDTO) {
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setFirstName(customerDTO.getFirstName());
        customerInfo.setLastName(customerDTO.getLastName());
        customerInfo.setEmail(customerDTO.getEmail());
        customerInfo.setPhoneNumber(customerDTO.getPhoneNumber());
        customerInfoRepository.save(customerInfo);
        return HttpResponsesMessage.builder()
                .message("Customer Information save successfully")
                .build();
    }

    @Override
    public HttpResponsesMessage deleteCustomerInformation(String id) {
        CustomerInfo  customerInfo = customerInfoRepository.findById(id)
                .orElseThrow(() -> new ElderlyCareException("Invalid Id", HttpStatus.BAD_REQUEST));

        customerInfoRepository.delete(customerInfo);
        return HttpResponsesMessage.builder()
                .message("Information delete successfully")
                .build();
    }

    @Override
    public List<CustomerDTO> getALLInformation() {
        List<CustomerInfo> customerInfo = customerInfoRepository.findAll();
        List<CustomerDTO> customerDTO = customerInfo.stream().map(a -> toDto(a)).collect(Collectors.toList());
        return customerDTO;
    }


    public static CustomerDTO toDto(CustomerInfo customerInfo){
        return CustomerDTO.builder()
                .firstName(customerInfo.getFirstName())
                .lastName(customerInfo.getLastName())
                .email(customerInfo.getEmail())
                .phoneNumber(customerInfo.getPhoneNumber())
                .build();
    }
}
