package com.example.controller;

import com.example.dto.CustomerInsertUpdateDto;
import com.example.entity.Customer;
import com.example.entity.Product;
import com.example.exceptionhandler.CustomerNotFoundException;
import com.example.response.AppResponse;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<AppResponse> saveCustomer(@Valid @RequestBody CustomerInsertUpdateDto dto) {

        customerService.saveCustomer(dto);

        AppResponse appResponse = new AppResponse();
        appResponse.setStatus(HttpStatus.OK.value());
        appResponse.setMessage("Success");
        appResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(appResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppResponse> getCustomerById(@PathVariable String id) {

        Customer theCustomer = customerService.getCustomerById(id);

        AppResponse appResponse = new AppResponse();

        if (theCustomer != null) {

            appResponse.setStatus(HttpStatus.FOUND.value());
            appResponse.setMessage(theCustomer);
            appResponse.setTimestamp(System.currentTimeMillis());

            return new ResponseEntity<>(appResponse, HttpStatus.FOUND);
        } else {
            throw new CustomerNotFoundException("No customer found with id - " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppResponse> updateCustomer(
            @PathVariable String id,
            @Valid @RequestBody CustomerInsertUpdateDto dto
    ) {
        Customer theCustomer = customerService.updateCustomer(id, dto);

        if (theCustomer != null) {

            AppResponse appResponse = new AppResponse();
            appResponse.setStatus(HttpStatus.OK.value());
            appResponse.setMessage(theCustomer);
            appResponse.setTimestamp(System.currentTimeMillis());

            return new ResponseEntity<>(appResponse, HttpStatus.OK);
        } else {
            throw new CustomerNotFoundException("No customer found with id - " + id);
        }
    }

    @GetMapping
    public ResponseEntity<Page<Customer>> findAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        Page<Customer> allCustomers = customerService.findAllCustomers(page, size);

        return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }
}















