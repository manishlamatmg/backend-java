package com.elderlyCare.api.customer.service;

import com.elderlyCare.api.base.HttpResponsesMessage;
import com.elderlyCare.api.customer.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    HttpResponsesMessage saveCustomerInformation(CustomerDTO customerInfo);

    HttpResponsesMessage deleteCustomerInformation(String id);

    List<CustomerDTO> getALLInformation();
}
