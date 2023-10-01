package com.example.service;

import com.example.dto.CreateOrderRequestDto;
import com.example.dto.CreateOrderResponseDto;
import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.entity.Product;
import com.example.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    private CustomerService customerService;

    private ProductService productService;

    @Autowired
    public OrderServiceImpl(
            OrderRepository orderRepository,
            CustomerService customerService,
            ProductService productService
            ) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.productService = productService;
    }

    @Override
    public CreateOrderResponseDto createOrder(CreateOrderRequestDto dto) {

        Customer theCustomer = customerService.getCustomerById(dto.getCustomerId());
        Product theProduct = productService.getProductById(dto.getProductId());

        BigDecimal price = new BigDecimal(theProduct.getProductPrice());
        BigDecimal qtt = new BigDecimal(dto.getQuantity());
        BigDecimal amount = price.multiply(qtt);

        Order order = new Order();
        order.setCustomer(theCustomer);
        order.setProduct(theProduct);
        order.setCustomerName(theCustomer.getCustomerName());
        order.setQuantity(dto.getQuantity());
        order.setAmount(amount);
        order.setOrderDate(LocalDateTime.now());

        Order theOrder = orderRepository.save(order);

        return orderRepository.getLatestOrderById(theOrder.getOrderId());
    }

    @Override
    public Page<CreateOrderResponseDto> findAllOrders(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return orderRepository.findAllOrders(pageable);
    }

}
