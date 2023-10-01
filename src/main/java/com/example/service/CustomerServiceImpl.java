package com.example.service;

import com.example.dto.CustomerInsertUpdateDto;
import com.example.entity.Customer;
import com.example.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void saveCustomer(CustomerInsertUpdateDto dto) {

        Customer customer = new Customer();
        BeanUtils.copyProperties(dto, customer);
        log.info("customer: " + customer);

        customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(String id) {

        Optional<Customer> theCustomer = customerRepository.findById(id);

        return theCustomer.isPresent() ? theCustomer.get() : null;
    }

    @Override
    public Customer updateCustomer(String id, CustomerInsertUpdateDto dto) {

        Customer theCustomer = getCustomerById(id);
        if (theCustomer != null) {
            BeanUtils.copyProperties(dto, theCustomer);
            return customerRepository.save(theCustomer);
        }

        return null;
    }

    @Override
    public Page<Customer> findAllCustomers(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return customerRepository.findAll(pageable);
    }
}


















