package edu.azati.shop.repository;

import edu.azati.shop.entity.Manufacturer;
import edu.azati.shop.entity.Product;
import edu.azati.shop.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ManufacturerRepo extends CrudRepository<Manufacturer, Long> {
    Manufacturer findManufacturerById(long id);
    void deleteById(long id);

}
