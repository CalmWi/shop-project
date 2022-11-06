package edu.azati.shop;

import edu.azati.shop.entity.Manufacturer;
import edu.azati.shop.entity.Product;
import edu.azati.shop.services.ManufacturerService;
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
public class ManufacturerServiceTest {
    @Mock
    ManufacturerService manufacturerService;

    @Test
    public void manufacturerServiceAddManufacturerTest() {
        Manufacturer manufacturer = new Manufacturer(1, "man2", null);
        doNothing().when(manufacturerService).addManufacturer(isA(Manufacturer.class));
        manufacturerService.addManufacturer(manufacturer);
        verify(manufacturerService, times(1)).addManufacturer(manufacturer);
    }

    @Test
    public void manufacturerServiceDeleteManufacturerTest() {
        doNothing().when(manufacturerService).deleteManufacturerById(isA(Long.class));
        manufacturerService.deleteManufacturerById(anyLong());
        verify(manufacturerService, times(1)).deleteManufacturerById(anyLong());
    }

    @Test
    public void manufacturerServiceUpdateManufacturerTest() {
        doNothing().when(manufacturerService).updateManufacturer(isA(Long.class), isA(String.class));
        manufacturerService.updateManufacturer(1, "lol");
        verify(manufacturerService, times(1)).updateManufacturer(1, "lol");
    }

    @Test
    public void manufacturerServiceAddProductToManufacturer() {
        doNothing().when(manufacturerService).addProductToManufacturer(isA(Long.class), isA(Product.class));
        //Product product = new Product().withName("wokew").withDescription("owefk woef woef").withProductCategory(ProductCategory.Clothes).withPrice(15.25);
        manufacturerService.addProductToManufacturer(anyLong(), any());
        verify(manufacturerService, times(1)).addProductToManufacturer(anyLong(), any());
    }

    @Test
    public void manufacturerServiceAddManufacturersTest() {
        Manufacturer manufacturer = new Manufacturer(1, "qwe", null);
        Manufacturer manufacturer1 = new Manufacturer(2, "fre", null);
        List<Manufacturer> manufacturers = List.of(manufacturer, manufacturer1);
        doNothing().when(manufacturerService).addManufacturers(isA(List.class));
        manufacturerService.addManufacturers(manufacturers);
        verify(manufacturerService, times(1)).addManufacturers(manufacturers);
    }

    @Test
    public void manufacturerServiceGetManufacturerByIdTest() {
        Manufacturer manufacturer = new Manufacturer(24, "efr", null);
        when(manufacturerService.getManufacturerById(24)).thenReturn(manufacturer);
        Assert.assertEquals(24, manufacturer.getId());
    }

    @Test
    public void manufacturerServiceGetManufacturerByNameTest() {
        Manufacturer manufacturer = new Manufacturer(24, "efr", null);
        when(manufacturerService.getManufacturerByName("efr")).thenReturn(manufacturer);
        Assert.assertEquals("efr", manufacturer.getName());
    }

    @Test
    public void manufacturerServiceGetAllManufacturersTest() {
        Manufacturer mockManufacturer = mock(Manufacturer.class);
        List<Manufacturer> manufacturers = new ArrayList<>();
        manufacturers.add(mockManufacturer);
        when(manufacturerService.getAllManufacturers()).thenReturn(manufacturers);
        Assert.assertEquals(1, manufacturers.size());
    }

    @Test
    public void manufacturerServiceGetManufacturerProductsTest() {
        List<Product> products = List.of(new Product(), new Product());
        when(manufacturerService.getManufacturerProducts(anyLong())).thenReturn(products);
        Assert.assertEquals(2, products.size());
    }
}
