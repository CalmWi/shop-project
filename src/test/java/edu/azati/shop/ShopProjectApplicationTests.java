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
    @Autowired
    UserService userService;
    @Autowired
    ManufacturerService manufacturerService;
    @Autowired
    ProductService productService;
    @Autowired
    StoreService storeService;

    @Test
    void contextLoads() {
    }

    @Test
    void UserServiceAddUserTest() {
        User user1 = new User().withId(6).withName("fwef").withSurname("tjyy").withPatronymic("wfewf").withUserRole(UserRole.Customer);
        userService.addUser(user1);
    }

    @Test
    void UserServiceDeleteUserTest() {
        userService.deleteUserById(12);
        User user2 = new User().withId(6).withName("euhrf").withSurname("tjyy").withPatronymic("wfewf").withUserRole(UserRole.Customer);
    }

    @Test
    void UserServiceUpdateUserTest() {
        userService.updateUser(19, "zxc", "wefl", "ektp", UserRole.Customer);
    }

    @Test
    void ManufacturerServiceAddManufacturerTest() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(1);
        manufacturer.setName("man2");
        manufacturerService.addManufacturer(manufacturer);
    }

    @Test
    void ManufacturerServiceDeleteManufacturerTest() {
        manufacturerService.deleteManufacturerById(8);
    }

    @Test
    void ManufacturerServiceUpdateManufacturerTest() {
        manufacturerService.updateManufacturer(1, "zxc");
    }

    @Test
    void ManufacturerServiceAddProductToManufacturer() {
        Product product = new Product().withName("wokew").withDescription("owefk woef woef").withProductCategory(ProductCategory.Clothes).withPrice(15.25);
        manufacturerService.addProductToManufacturer(27, product);
        List<Product> products = manufacturerService.getManufacturerById(27).getProducts();
    }

    @Test
    void StoreServiceAddStoreTest() {
        Product product = new Product().withName("wokew").withDescription("owefk woef woef").withProductCategory(ProductCategory.Clothes).withPrice(15.25);
        Product product1 = new Product().withName("qwe").withDescription("jig eurhf").withProductCategory(ProductCategory.Books).withPrice(48.26);
        Product product2 = new Product().withName("asd").withDescription("lgrjt hb").withProductCategory(ProductCategory.Clothes).withPrice(12.95);
        List<Product> products = List.of(product, product1, product2);
        Store store = new Store(1, products);
        storeService.addStore(store);
    }

    @Test
    void StoreServiceDeleteStoreTest() {
        storeService.deleteStoreById(37);
    }

    @Test
    void StoreServiceAddProductToStoreTest() {
        Product product = new Product().withName("kij").withDescription("owefk woef woef").withProductCategory(ProductCategory.Clothes).withPrice(15.25);
        storeService.addProductToStore(39,product);
    }
}
