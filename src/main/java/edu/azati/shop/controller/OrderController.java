package edu.azati.shop.controller;

import edu.azati.shop.entity.Product;
import edu.azati.shop.enums.ProductCategory;
import edu.azati.shop.services.JasperService;
import edu.azati.shop.services.OrderService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;
import java.util.List;

@Controller
@RequestMapping("/api")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    JasperService jasperService;

    @GetMapping("/orders")
    @PreAuthorize("hasAuthority('write')")
    public String showOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }

    @GetMapping("/generate-order-pdf/{id}")
    @PreAuthorize("hasAuthority('read')")
    public String generateOrderPdf(@PathVariable("id") long id) throws JRException, FileNotFoundException {
        jasperService.generatePdf(orderService.getOrderProducts(id));
        return "redirect:/orders";
    }

    @GetMapping("/show-order-products/{id}")
    @PreAuthorize("hasAuthority('read')")
    public String showOrderProducts(@PathVariable("id") long id, Model model) {
        List<Product> products = orderService.getOrderProducts(id);
        model.addAttribute("products", products);
        model.addAttribute("categories", ProductCategory.values());
        return "products";
    }
}
