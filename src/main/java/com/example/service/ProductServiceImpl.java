package com.example.service;

import com.example.dto.ProductInsertUpdateDto;
import com.example.entity.Product;
import com.example.repository.ProductRepository;
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
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void saveProduct(ProductInsertUpdateDto dto) {

        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        log.info("product: " + product);

        productRepository.save(product);
    }

    @Override
    public Product getProductById(String id) {

        Optional<Product> theProduct = productRepository.findById(id);

        return theProduct.isPresent() ? theProduct.get() : null;
    }

    @Override
    public Product updateProduct(String id, ProductInsertUpdateDto dto) {

        Product theProduct = getProductById(id);
        if (theProduct != null) {
            BeanUtils.copyProperties(dto, theProduct);
            return productRepository.save(theProduct);
        }

        return null;
    }

    @Override
    public Page<Product> findAllProducts(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return productRepository.findAll(pageable);
    }
}
