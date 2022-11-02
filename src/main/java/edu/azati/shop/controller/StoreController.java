package edu.azati.shop.controller;

import edu.azati.shop.entity.Product;
import edu.azati.shop.entity.Store;
import edu.azati.shop.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StoreController {
    @Autowired
    StoreService storeService;

    @GetMapping("/stores")
    public String showManufacturer(Model model) {
        model.addAttribute("stores", storeService.getAllStores());
        return "stores";
    }

    @GetMapping("/signup-store")
    public String showSignUpForm(Store store) {
        return "add-store";
    }

    @PostMapping("/add-store")
    public String addStore(@Validated Store store, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-store";
        }

        storeService.addStore(store);
        return "redirect:/stores";
    }

    @GetMapping("/edit-store/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Store store = storeService.getStoreById(id);
        model.addAttribute("manufacturer", store);
        return "update-store";
    }

    @PostMapping("/update-store/{id}")
    public String updateStore(@PathVariable("id") long id, @Validated Store store, BindingResult result, Model model) {
        if (result.hasErrors()) {
            store.setId(id);
            return "update-store";
        }
        storeService.updateStore(store.getId(), store.getProducts());
        return "redirect:/stores";
    }

    @GetMapping("/delete-store/{id}")
    public String deleteStore(@PathVariable("id") long id, Model model) {
        Store store = storeService.getStoreById(id);
        storeService.deleteStoreById(store.getId());
        return "redirect:/stores";
    }

    @GetMapping("/show-store-products/{id}")
    public String showManufacturerProducts(@PathVariable("id") long id, Model model) {
        List<Product> products = storeService.getStoreById(id).getProducts();
        model.addAttribute("products", products);
        return "products";
    }
}
