package edu.azati.shop.controller;

import edu.azati.shop.entity.Product;
import edu.azati.shop.enums.ProductCategory;
import edu.azati.shop.services.OrderService;
import edu.azati.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;

    @GetMapping("/products")
    @PreAuthorize("hasAuthority('read')")
    public String showProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("categories", ProductCategory.values());
        return "products";
    }
    @GetMapping("/get-products-category/{category}")
    @PreAuthorize("hasAuthority('read')")
    public String showProductsByCategory(@PathVariable("category") String category,Model model) {
        List<Product> products = productService.getAllProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("categories", ProductCategory.values());
        return "products";
    }
    @GetMapping("/signup-product")
    @PreAuthorize("hasAuthority('write')")
    public String showSignUpForm(Product product) {
        return "add-product";
    }

    @PostMapping("/add-product")
    @PreAuthorize("hasAuthority('write')")
    public String addProduct(@Validated Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "add-product";
        }

        productService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit-product/{id}")
    @PreAuthorize("hasAuthority('write')")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "update-product";
    }

    @PostMapping("/update-product/{id}")
    @PreAuthorize("hasAuthority('write')")
    public String updateProduct(@PathVariable("id") long id, @Validated Product product, BindingResult result) {
        if (result.hasErrors()) {
            product.setId(id);
            return "update-product";
        }
        productService.updateProduct(product.getId(), product.getName(), product.getDescription(), product.getProductCategory(), product.getPrice());
        return "redirect:/products";
    }

    @GetMapping("/delete-product/{id}")
    @PreAuthorize("hasAuthority('write')")
    public String deleteProduct(@PathVariable("id") long id) {
        Product product = productService.getProductById(id);
        productService.deleteProductById(product.getId());
        return "redirect:/products";
    }

    @GetMapping("/get-product/{id}")
    @PreAuthorize("hasAuthority('read')")
    public String getProduct(@PathVariable("id") long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product";
    }

    @GetMapping("/add-product-to-order/{id}")
    @PreAuthorize("hasAuthority('read')")
    public String addProductToOrder(@PathVariable("id") long id) {
        Product product = productService.getProductById(id);
        orderService.addProductToOrder(1, product);
        return "redirect:/products";
    }
}
