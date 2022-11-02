package edu.azati.shop.services;

import edu.azati.shop.entity.Order;
import edu.azati.shop.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService {
    @Autowired
    OrderRepo orderRepo;

    public void addOrder(Order order) {
        orderRepo.save(order);
    }

    public void addOrders(ArrayList<Order> orders) {
        orderRepo.saveAll(orders);
    }

    public Order getOrderById(long id) {
        Order order = orderRepo.findOrderById(id);
        return order;
    }
}
