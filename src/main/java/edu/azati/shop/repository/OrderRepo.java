package edu.azati.shop.repository;

import edu.azati.shop.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends CrudRepository<Order, Long> {
    Order findOrderById(long id);
}
