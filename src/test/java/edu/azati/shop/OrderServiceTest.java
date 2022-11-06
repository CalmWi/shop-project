package edu.azati.shop;

import edu.azati.shop.entity.Order;
import edu.azati.shop.enums.PaymentType;
import edu.azati.shop.services.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    @Mock
    OrderService orderService;

    @Test
    public void orderServiceAddOrderTest() {
        Order order = new Order(1, PaymentType.Card, null);
        doNothing().when(orderService).addOrder(isA(Order.class));
        orderService.addOrder(order);
        verify(orderService, times(1)).addOrder(order);
    }

    @Test
    public void orderServiceAddOrdersTest() {
        Order order = new Order(1, PaymentType.Card, null);
        Order order1 = new Order(1, PaymentType.Cash, null);
        List<Order> orders = List.of(order, order1);
        doNothing().when(orderService).addOrders(isA(List.class));
        orderService.addOrders(orders);
        verify(orderService, times(1)).addOrders(orders);

    }

    @Test
    public void orderServiceGetOrderByIdTest() {
        Order order = new Order(1, PaymentType.Card, null);
        when(orderService.getOrderById(1)).thenReturn(order);
        Assert.assertEquals(1, order.getId());
    }
}
