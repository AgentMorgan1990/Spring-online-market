package com.geekbrains.spring.web.core.mappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.geekbrains.spring.web.core.entities.Order;

public class OrderMapper {
    private final Connection connection;
    public OrderMapper(Connection connection) {
        this.connection = connection;
    }

    public Order findById(long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT ID, USERNAME, TOTAL_PRICE, ADDRESS,PHONE, CREATED_AT, UPDATED_AT " +
                        "FROM ORDERS WHERE ID = ?");
        statement.setLong(1, id);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong(1));
                order.setUsername(resultSet.getString(2));
                order.setItems(null);
                order.setTotalPrice(resultSet.getBigDecimal(3));
                order.setAddress(resultSet.getString(4));
                order.setPhone(resultSet.getString(5));
                return order;
            }
        }
        throw new SQLException();
    }
}