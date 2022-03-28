package com.geekbrains.spring.web.core.services;

import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.geekbrains.spring.web.api.carts.CartDto;
import com.geekbrains.spring.web.api.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.core.entities.Order;
import com.geekbrains.spring.web.core.entities.OrderItem;


/**
 * Построитель ордеров
 */

public class OrderBuilder {

    @Autowired
    private ProductsService productsService;

    private final Order order;

    public OrderBuilder() {
        order = new Order();
    }

    public OrderBuilder address(String address) {
        order.setAddress(address);
        return this;
    }

    public OrderBuilder phone(String phone) {
        order.setPhone(phone);
        return this;
    }

    public OrderBuilder username(String username) {
        order.setUsername(username);
        return this;
    }

    public OrderBuilder totalPrice(BigDecimal totalPrice) {
        order.setTotalPrice(totalPrice);
        return this;
    }

    public OrderBuilder items(CartDto currentCart) {
        List<OrderItem> items = currentCart.getItems().stream()
                .map(o -> {
                    OrderItem item = new OrderItem();
                    item.setOrder(order);
                    item.setQuantity(o.getQuantity());
                    item.setPricePerProduct(o.getPricePerProduct());
                    item.setPrice(o.getPrice());
                    item.setProduct(productsService.findById(o.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found")));
                    return item;
                }).collect(Collectors.toList());
        order.setItems(items);
        return this;
    }

    public Order build() {
        return order;
    }
}
