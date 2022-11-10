package edu.azati.shop.controller;

import edu.azati.shop.entity.Product;
import edu.azati.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public String showProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/signup-product")
    public String showSignUpForm(Product product) {
        return "add-product";
    }

    @PostMapping("/add-product")
    public String addProduct(@Validated Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "add-product";
        }

        productService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit-product/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "update-product";
    }

    @PostMapping("/update-product/{id}")
    public String updateProduct(@PathVariable("id") long id, @Validated Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            product.setId(id);
            return "update-product";
        }
        productService.updateProduct(product.getId(), product.getName(), product.getDescription(), product.getProductCategory(), product.getPrice());
        return "redirect:/products";
    }

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable("id") long id) {
        Product product = productService.getProductById(id);
        productService.deleteProductById(product.getId());
        return "redirect:/products";
    }

    @GetMapping("/get-product/{name}")
    public String getProduct(@PathVariable("name") String name, Model model) {
        Product product = productService.getProductByName(name);
        model.addAttribute("product", product);
        return "product";
    }
}
