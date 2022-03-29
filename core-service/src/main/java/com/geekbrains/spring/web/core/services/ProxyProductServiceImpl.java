package com.geekbrains.spring.web.core.services;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.core.entities.Product;

@Service
public class ProxyProductServiceImpl implements ProductService {

    @Autowired
    private ProductsServiceImpl productsService;

    private final HashMap<Long, Optional<Product>> products = new HashMap<>();

    @Override
    public Page<Product> findAll(Integer minPrice, Integer maxPrice, String partTitle, Integer page) {
        return productsService.findAll(minPrice,maxPrice,partTitle,page);
    }

    @Override
    public Optional<Product> findById(Long id) {
        Optional<Product> product;
        if (products.containsKey(id)){
            product = products.get(id);
        } else {
            product = productsService.findById(id);
        }
        return product;
    }

    @Override
    public void deleteById(Long id) {
        productsService.deleteById(id);
    }

    @Override
    public Product save(Product product) {
        return productsService.save(product);
    }

    @Override
    public Product update(ProductDto productDto) {
        return productsService.update(productDto);
    }
}
