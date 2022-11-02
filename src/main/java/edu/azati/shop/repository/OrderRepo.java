package edu.azati.shop.repository;

import edu.azati.shop.entity.Order;
import edu.azati.shop.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepo extends CrudRepository<Order, Long> {
    Order findOrderById(long id);
}
