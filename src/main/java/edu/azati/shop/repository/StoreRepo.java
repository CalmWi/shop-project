package edu.azati.shop.repository;

import edu.azati.shop.entity.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepo extends CrudRepository<Store, Long> {
    Store findById(long id);
}
