package com.geekbrains.spring.web.core.services;

import com.geekbrains.spring.web.api.carts.CartDto;
import com.geekbrains.spring.web.api.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.api.core.OrderDetailsDto;
import com.geekbrains.spring.web.core.entities.Order;
import com.geekbrains.spring.web.core.entities.OrderItem;
import com.geekbrains.spring.web.core.integrations.CartServiceIntegration;
import com.geekbrains.spring.web.core.repositories.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;
    private final CartServiceIntegration cartServiceIntegration;

    @Transactional
    public void createOrder(String username, OrderDetailsDto orderDetailsDto) {

        CartDto currentCart = cartServiceIntegration.getUserCart(username);

        Order order = (new OrderBuilder())
                .address(orderDetailsDto.getAddress())
                .phone(orderDetailsDto.getPhone())
                .username(username)
                .totalPrice(currentCart.getTotalPrice())
                .items(currentCart)
                .build();

        ordersRepository.save(order);

        cartServiceIntegration.clearUserCart(username);
    }

    public List<Order> findOrdersByUsername(String username) {
        return ordersRepository.findAllByUsername(username);
    }

    public Optional<Order> findById(Long id) {
        return ordersRepository.findById(id);
    }
}
