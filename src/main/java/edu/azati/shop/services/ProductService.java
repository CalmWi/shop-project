package edu.azati.shop.services;

import edu.azati.shop.entity.Manufacturer;
import edu.azati.shop.entity.Order;
import edu.azati.shop.entity.Product;
import edu.azati.shop.entity.Store;
import edu.azati.shop.enums.ProductCategory;
import edu.azati.shop.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;

    public void addProductToStore(Store store, Product product) {
        store.getProducts().add(product);
    }

    public void addProductsToStore(Store store, ArrayList<Product> products) {
        store.getProducts().addAll(products);
    }

    public void addProductToOrder(Order order, Product product) {
        order.getProducts().add(product);
    }

    public void addProductsToOrder(Order order, ArrayList<Product> products) {
        order.getProducts().addAll(products);
    }

    public void addProductToManufacturer(Manufacturer manufacturer, Product product) {
        productRepo.save(product);
    }

    public Product getProductById(long id) {
        return productRepo.findProductById(id);
    }
    public void addProduct(Product product) {
        productRepo.save(product);
    }
    public void updateProduct(long id, String name, String description, ProductCategory productCategory, double price) {
        Product product = getProductById(id);
        product.setName(name);
        product.setDescription(description);
        product.setProductCategory(productCategory);
        product.setPrice(price);
        productRepo.save(product);
    }
    public void deleteProductById(long id) {
        productRepo.deleteById(id);
    }
    public List<Product> getAllProducts() {
        return StreamSupport.stream(productRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }


}
