package com.geekbrains.spring.web.core.mappers;

import java.util.HashMap;
import java.util.Map;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.geekbrains.spring.web.core.entities.Order;

@Service
public class IdentityOrderMapService {

    Connection connection = getNewConnection();

    private Connection getNewConnection() {
        Connection connection = null;
        try {
            String url = "jdbc:h2:mem:mydatabase";
            String user = "sa";
            String passwd = "";
            connection = DriverManager.getConnection(url, user, passwd);
        } catch (SQLException e) {
        }

        return connection;
    }

    private final Map<Long, Order> orderMap = new HashMap();

    public void addPerson(Order order) {
        orderMap.put(order.getId(), order);
    }

    public Order getOrder(Long key) throws SQLException {
        Order order;
        if (orderMap.containsKey(key)) {
            return orderMap.get(key);
        } else {
            OrderMapper orderMapper = new OrderMapper(connection);
            order = orderMapper.findById(key);
            addPerson(order);
            return order;
        }
    }

}
