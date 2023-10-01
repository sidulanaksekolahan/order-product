package com.example.service;

import com.example.dto.CustomerInsertUpdateDto;
import com.example.entity.Customer;
import org.springframework.data.domain.Page;

public interface CustomerService {

    void saveCustomer(CustomerInsertUpdateDto dto);

    Customer getCustomerById(String id);

    Customer updateCustomer(String id, CustomerInsertUpdateDto dto);

    Page<Customer> findAllCustomers(int page, int size);
}
