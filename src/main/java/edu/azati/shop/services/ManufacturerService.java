package edu.azati.shop.services;

import edu.azati.shop.entity.Manufacturer;
import edu.azati.shop.entity.Product;
import edu.azati.shop.repository.ManufacturerRepo;
import edu.azati.shop.repository.ProductRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ManufacturerService {
    @Autowired
    ManufacturerRepo manufacturerRepo;
    @Autowired
    ProductRepo productRepo;
    private static final Logger LOG = LoggerFactory.getLogger(ManufacturerService.class);

    public void addManufacturer(Manufacturer manufacturer) {
        manufacturerRepo.save(manufacturer);
    }

    public void addProductToManufacturer(long id, Product product) {
        Manufacturer manufacturer = getManufacturerById(id);
        product.setManufacturer(manufacturer);
        productRepo.save(product);
    }

    public void addManufacturers(List<Manufacturer> manufacturers) {
        manufacturerRepo.saveAll(manufacturers);
    }

    public Manufacturer getManufacturerById(long id) {
        LOG.info("Received manufacturer with id = {}", id);
        return manufacturerRepo.findManufacturerById(id);
    }

    public Manufacturer getManufacturerByName(String name) {
        return manufacturerRepo.findManufacturerByName(name);
    }

    public void updateManufacturer(long id, String name) {
        Manufacturer manufacturer = manufacturerRepo.findManufacturerById(id);
        manufacturer.setName(name);
        manufacturerRepo.save(manufacturer);
        LOG.info("Update manufacturer with id = {}", id);
    }

    public List<Manufacturer> getAllManufacturers() {
        return StreamSupport.stream(manufacturerRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void deleteManufacturerById(long id) {
        manufacturerRepo.deleteById(id);
        LOG.info("Remote manufacturer with id = {}", id);
    }

    public List<Product> getManufacturerProducts(long id) {
        return getManufacturerById(id).getProducts();
    }
}
