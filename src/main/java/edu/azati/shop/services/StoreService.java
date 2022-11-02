package edu.azati.shop.services;

import edu.azati.shop.entity.Product;
import edu.azati.shop.entity.Store;
import edu.azati.shop.repository.ProductRepo;
import edu.azati.shop.repository.StoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StoreService {
    @Autowired
    StoreRepo storeRepo;
    @Autowired
    ProductRepo productRepo;

    public StoreService(StoreRepo storeRepo) {
        this.storeRepo = storeRepo;
    }

    public void addStore(Store store) {
        productRepo.saveAll(store.getProducts());
        storeRepo.save(store);
    }

    public void addStores(ArrayList<Store> stores) {
        storeRepo.saveAll(stores);
    }

    public void deleteStoreById(long id) {
        storeRepo.deleteById(id);
    }

    public void updateStore(long id, List<Product> products) {
        Store store = getStoreById(id);
        store.setProducts(products);
        storeRepo.save(store);
    }

    public Store getStoreById(long id) {
        return storeRepo.findById(id);
    }
    public void addProductToStore(long id, Product product) {
        Store store = getStoreById(id);
        product.setStore(store);
        productRepo.save(product);
    }

    public List<Store> getAllStores() {
        return StreamSupport.stream(storeRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
