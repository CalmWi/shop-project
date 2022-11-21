package edu.azati.shop.services;

import edu.azati.shop.entity.Order;
import edu.azati.shop.entity.Product;
import edu.azati.shop.repository.OrderRepo;
import edu.azati.shop.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderService {
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    ProductRepo productRepo;

    public void addOrder(Order order) {
        orderRepo.save(order);
    }

    public void addOrders(List<Order> orders) {
        orderRepo.saveAll(orders);
    }

    public Order getOrderById(long id) {
        Order order = orderRepo.findOrderById(id);
        return order;
    }

    public List<Order> getAllOrders() {
        return StreamSupport.stream(orderRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public List<Product> getOrderProducts(long id) {
        return getOrderById(id).getProducts();
    }

    public void addProductToOrder(long id, Product product) {
        Order order = getOrderById(id);
        product.setOrder(order);
        productRepo.save(product);
    }
}
