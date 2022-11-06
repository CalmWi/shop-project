package edu.azati.shop;

import edu.azati.shop.entity.Product;
import edu.azati.shop.enums.ProductCategory;
import edu.azati.shop.services.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @Mock
    ProductService productService;

    @Test
    public void productServiceAddProductTest() {
        Product product = new Product().withName("wokew").withDescription("owefk woef woef").withProductCategory(ProductCategory.Clothes).withPrice(15.25);
        doNothing().when(productService).addProduct(isA(Product.class));
        productService.addProduct(product);
        verify(productService, times(1)).addProduct(product);
    }

    @Test
    public void productServiceAddProductsTest() {
        Product product = new Product().withName("wokew").withDescription("owefk woef woef").withProductCategory(ProductCategory.Clothes).withPrice(15.25);
        Product product1 = new Product().withName("wokew").withDescription("owefk woef woef").withProductCategory(ProductCategory.Clothes).withPrice(15.25);
        List<Product> products = List.of(product, product1);
        doNothing().when(productService).addProducts(isA(List.class));
        productService.addProducts(products);
        verify(productService, times(1)).addProducts(products);
    }

    @Test
    public void productServiceDeleteProductTest() {
        doNothing().when(productService).deleteProductById(isA(Long.class));
        productService.deleteProductById(anyLong());
        verify(productService, times(1)).deleteProductById(anyLong());

    }

    @Test
    public void productServiceUpdateProductTest() {
        doNothing().when(productService).updateProduct(isA(Long.class), isA(String.class), isA(String.class), isA(ProductCategory.class), isA(Double.class));
        productService.updateProduct(19, "zxc", "wefl", ProductCategory.Books, 15.26);
        verify(productService, times(1)).updateProduct(19, "zxc", "wefl", ProductCategory.Books, 15.26);

    }

    @Test
    public void productServiceGetAllProductsTest() {
        List<Product> products = productService.getAllProducts();
        Product mockProduct = mock(Product.class);
        products.add(mockProduct);
        when(productService.getAllProducts()).thenReturn(products);
        Assert.assertEquals(1, products.size());
    }

}
