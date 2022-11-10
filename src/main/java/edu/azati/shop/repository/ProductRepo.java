package edu.azati.shop.repository;

import edu.azati.shop.entity.Product;
import edu.azati.shop.enums.ProductCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {
    List<Product> findAllByName(String name);
    Product findProductById(long id);
    Product findProductsByName(String name);
}
