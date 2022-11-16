package edu.azati.shop.controller;

import edu.azati.shop.entity.Product;
import edu.azati.shop.entity.Store;
import edu.azati.shop.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
public class StoreController {
    @Autowired
    StoreService storeService;

    @GetMapping("/stores")
    @PreAuthorize("hasAuthority('write')")
    public String showStore(Model model) {
        model.addAttribute("stores", storeService.getAllStores());
        return "stores";
    }

    @GetMapping("/signup-store")
    @PreAuthorize("hasAuthority('write')")
    public String showSignUpForm(Store store) {
        return "add-store";
    }

    @PostMapping("/add-store")
    @PreAuthorize("hasAuthority('write')")
    public String addStore(@Validated Store store, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-store";
        }

        storeService.addStore(store);
        return "redirect:/stores";
    }

    @GetMapping("/edit-store/{id}")
    @PreAuthorize("hasAuthority('write')")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Store store = storeService.getStoreById(id);
        model.addAttribute("manufacturer", store);
        return "update-store";
    }

    @PostMapping("/update-store/{id}")
    @PreAuthorize("hasAuthority('write')")
    public String updateStore(@PathVariable("id") long id, @Validated Store store, BindingResult result, Model model) {
        if (result.hasErrors()) {
            store.setId(id);
            return "update-store";
        }
        storeService.updateStore(store.getId(), store.getProducts());
        return "redirect:/stores";
    }

    @GetMapping("/delete-store/{id}")
    @PreAuthorize("hasAuthority('write')")
    public String deleteStore(@PathVariable("id") long id, Model model) {
        Store store = storeService.getStoreById(id);
        storeService.deleteStoreById(store.getId());
        return "redirect:/stores";
    }

    @GetMapping("/show-store-products/{id}")
    @PreAuthorize("hasAuthority('write')")
    public String showStoreProducts(@PathVariable("id") long id, Model model) {
        List<Product> products = storeService.getStoreById(id).getProducts();
        model.addAttribute("products", products);
        return "products";
    }
}
