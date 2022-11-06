package edu.azati.shop;

import edu.azati.shop.entity.Manufacturer;
import edu.azati.shop.entity.Product;
import edu.azati.shop.entity.Store;
import edu.azati.shop.entity.User;
import edu.azati.shop.enums.ProductCategory;
import edu.azati.shop.enums.UserRole;
import edu.azati.shop.services.ManufacturerService;
import edu.azati.shop.services.ProductService;
import edu.azati.shop.services.StoreService;
import edu.azati.shop.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ShopProjectApplicationTests {
    @Test
    void contextLoads() {
    }
}
