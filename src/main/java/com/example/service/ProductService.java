package com.example.service;

import com.example.dto.ProductInsertUpdateDto;
import com.example.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    void saveProduct(ProductInsertUpdateDto dto);

    Product getProductById(String id);

    Product updateProduct(String id, ProductInsertUpdateDto dto);

    Page<Product> findAllProducts(int page, int size);
}
