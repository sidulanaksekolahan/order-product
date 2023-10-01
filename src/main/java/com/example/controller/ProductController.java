package com.example.controller;

import com.example.dto.ProductInsertUpdateDto;
import com.example.entity.Product;
import com.example.exceptionhandler.ProductNotFoundException;
import com.example.response.AppResponse;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<AppResponse> saveProduct(@Valid @RequestBody ProductInsertUpdateDto dto) {

        productService.saveProduct(dto);

        AppResponse appResponse = new AppResponse();
        appResponse.setStatus(HttpStatus.OK.value());
        appResponse.setMessage("Success");
        appResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(appResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppResponse> getProductById(@PathVariable String id) {

        Product theProduct = productService.getProductById(id);

        AppResponse appResponse = new AppResponse();

        if (theProduct != null) {

            appResponse.setStatus(HttpStatus.FOUND.value());
            appResponse.setMessage(theProduct);
            appResponse.setTimestamp(System.currentTimeMillis());

            return new ResponseEntity<>(appResponse, HttpStatus.FOUND);
        } else {
            throw new ProductNotFoundException("No product found with id - " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppResponse> updateProduct(
            @PathVariable String id,
            @Valid @RequestBody ProductInsertUpdateDto dto
    ) {
        Product theProduct = productService.updateProduct(id, dto);

        if (theProduct != null) {

            AppResponse appResponse = new AppResponse();
            appResponse.setStatus(HttpStatus.OK.value());
            appResponse.setMessage(theProduct);
            appResponse.setTimestamp(System.currentTimeMillis());

            return new ResponseEntity<>(appResponse, HttpStatus.OK);
        } else {
            throw new ProductNotFoundException("No product found with id - " + id);
        }
    }

    @GetMapping
    public ResponseEntity<Page<Product>> findAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        Page<Product> allProducts = productService.findAllProducts(page, size);

        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

}

















