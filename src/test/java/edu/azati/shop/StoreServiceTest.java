package edu.azati.shop;

import edu.azati.shop.entity.Product;
import edu.azati.shop.entity.Store;
import edu.azati.shop.enums.ProductCategory;
import edu.azati.shop.services.StoreService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StoreServiceTest {
    @Mock
    StoreService storeService;

    @Test
    public void storeServiceAddStoreTest() {
        Product product = new Product().withName("wokew").withDescription("owefk woef woef").withProductCategory(ProductCategory.Clothes).withPrice(15.25);
        Product product1 = new Product().withName("qwe").withDescription("jig eurhf").withProductCategory(ProductCategory.Books).withPrice(48.26);
        Product product2 = new Product().withName("asd").withDescription("lgrjt hb").withProductCategory(ProductCategory.Clothes).withPrice(12.95);
        List<Product> products = List.of(product, product1, product2);
        Store store = new Store(1, products);
        doNothing().when(storeService).addStore(isA(Store.class));
        storeService.addStore(store);
        verify(storeService, times(1)).addStore(store);
    }

    @Test
    public void storeServiceAddStoresTest() {
        Product product = new Product().withName("wokew").withDescription("owefk woef woef").withProductCategory(ProductCategory.Clothes).withPrice(15.25);
        Product product1 = new Product().withName("qwe").withDescription("jig eurhf").withProductCategory(ProductCategory.Books).withPrice(48.26);
        Product product2 = new Product().withName("asd").withDescription("lgrjt hb").withProductCategory(ProductCategory.Clothes).withPrice(12.95);
        List<Product> products = List.of(product, product1, product2);
        Store store = new Store(1, products);
        Store store1 = new Store(1, products);
        List<Store> stores = List.of(store, store1);
        doNothing().when(storeService).addStores(isA(List.class));
        storeService.addStores(stores);
        verify(storeService, times(1)).addStores(stores);

    }

    @Test
    public void storeServiceGetStoreByIdTest() {
        Store store = new Store(4, null);
        when(storeService.getStoreById(4)).thenReturn(store);
        Assert.assertEquals(4, store.getId());
    }

    @Test
    public void storeServiceDeleteStoreTest() {
        doNothing().when(storeService).deleteStoreById(isA(Long.class));
        storeService.deleteStoreById(anyLong());
        verify(storeService, times(1)).deleteStoreById(anyLong());

    }

    @Test
    public void storeServiceGetAllStoresTest() {
        Store mockStore = mock(Store.class);
        List<Store> stores = new ArrayList<>();
        stores.add(mockStore);
        when(storeService.getAllStores()).thenReturn(stores);
        Assert.assertEquals(1, stores.size());
    }

    @Test
    public void storeServiceAddProductToStoreTest() {
        doNothing().when(storeService).addProductToStore(isA(Long.class), isA(Product.class));
        //Product product = new Product().withName("wokew").withDescription("owefk woef woef").withProductCategory(ProductCategory.Clothes).withPrice(15.25);
        storeService.addProductToStore(anyLong(), any());
        verify(storeService, times(1)).addProductToStore(anyLong(), any());
    }

}
