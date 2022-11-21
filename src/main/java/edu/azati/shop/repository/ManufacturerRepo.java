package edu.azati.shop.repository;

import edu.azati.shop.entity.Manufacturer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepo extends CrudRepository<Manufacturer, Long> {
    Manufacturer findManufacturerById(long id);

    Manufacturer findManufacturerByName(String name);

    void deleteById(long id);

}
