package com.geekbrains.spring.web.core.services;

import com.geekbrains.spring.web.api.carts.CartDto;
import com.geekbrains.spring.web.api.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.api.core.OrderDetailsDto;
import com.geekbrains.spring.web.core.entities.Order;
import com.geekbrains.spring.web.core.entities.OrderItem;
import com.geekbrains.spring.web.core.entities.OrderStatus;
import com.geekbrains.spring.web.core.integrations.CartServiceIntegration;
import com.geekbrains.spring.web.core.repositories.OrdersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class
OrderService {
    private final OrdersRepository ordersRepository;
    private final CartServiceIntegration cartServiceIntegration;
    private final ProductsService productsService;

    @Transactional
    public void createOrder(String username, OrderDetailsDto orderDetailsDto) {
        CartDto currentCart = cartServiceIntegration.getUserCart(username);
        Order order = new Order();
        order.setOrderStatus(OrderStatus.CREATED);
        order.setCity(orderDetailsDto.getCity());
        order.setCountryCode(orderDetailsDto.getCountryCode());
        order.setFlat(orderDetailsDto.getFlat());
        order.setStreet(orderDetailsDto.getStreet());
        order.setHouse(orderDetailsDto.getHouse());
        order.setName(orderDetailsDto.getName());
        order.setSurname(orderDetailsDto.getSurname());
        order.setDistrict(orderDetailsDto.getDistrict());
        order.setPostalCode(orderDetailsDto.getPostalCode());
        order.setPhone(orderDetailsDto.getPhone());
        order.setUsername(username);
        order.setTotalPrice(currentCart.getTotalPrice());
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
        ordersRepository.save(order);
        cartServiceIntegration.clearUserCart(username);
    }

    public List<Order> findOrdersByUsername(String username) {
        return ordersRepository.findAllByUsername(username);
    }

    public Optional<Order> findById(Long id) {
        return ordersRepository.findById(id);
    }

    public Order changeStatus(OrderStatus orderStatus,Long id){
       Order order = ordersRepository.findById(id).get();
       order.setOrderStatus(orderStatus);
        return ordersRepository.save(order);
    }
}
