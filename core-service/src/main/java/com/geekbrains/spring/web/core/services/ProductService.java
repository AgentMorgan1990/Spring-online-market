package com.geekbrains.spring.web.core.services;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.core.entities.Product;

public interface ProductService {
    Page<Product> findAll(Integer minPrice, Integer maxPrice, String partTitle, Integer page);
    Optional<Product> findById(Long id);
    void deleteById(Long id);
    Product save(Product product);
    Product update(ProductDto productDto);
}
