package edu.azati.shop.controller;

import edu.azati.shop.entity.Manufacturer;
import edu.azati.shop.entity.Product;
import edu.azati.shop.enums.ProductCategory;
import edu.azati.shop.services.ManufacturerService;
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
public class ManufacturerController {
    @Autowired
    ManufacturerService manufacturerService;

    @GetMapping("/manufacturers")
    @PreAuthorize("hasAuthority('read')")
    public String showManufacturers(Model model) {
        model.addAttribute("manufacturers", manufacturerService.getAllManufacturers());
        return "manufacturers";
    }

    @GetMapping("/signup-manufacturer")
    @PreAuthorize("hasAuthority('write')")
    public String showSignUpForm(Manufacturer manufacturer) {
        return "add-manufacturer";
    }

    @PostMapping("/add-manufacturer")
    @PreAuthorize("hasAuthority('write')")
    public String addManufacturer(@Validated Manufacturer manufacturer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-manufacturer";
        }

        manufacturerService.addManufacturer(manufacturer);
        return "redirect:/manufacturers";
    }

    @GetMapping("/edit-manufacturer/{id}")
    @PreAuthorize("hasAuthority('write')")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Manufacturer manufacturer = manufacturerService.getManufacturerById(id);
        model.addAttribute("manufacturer", manufacturer);
        return "update-manufacturer";
    }

    @PostMapping("/update-manufacturer/{id}")
    @PreAuthorize("hasAuthority('write')")
    public String updateManufacturer(@PathVariable("id") long id, @Validated Manufacturer manufacturer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            manufacturer.setId(id);
            return "update-manufacturer";
        }
        manufacturerService.updateManufacturer(manufacturer.getId(), manufacturer.getName());
        return "redirect:/manufacturers";
    }

    @GetMapping("/delete-manufacturer/{id}")
    @PreAuthorize("hasAuthority('write')")
    public String deleteManufacturer(@PathVariable("id") long id, Model model) {
        Manufacturer manufacturer = manufacturerService.getManufacturerById(id);
        manufacturerService.deleteManufacturerById(manufacturer.getId());
        return "redirect:/manufacturers";
    }

    @GetMapping("/show-manufacturer-products/{id}")
    @PreAuthorize("hasAuthority('read')")
    public String showManufacturerProducts(@PathVariable("id") long id, Model model) {
        List<Product> products = manufacturerService.getManufacturerProducts(id);
        model.addAttribute("products", products);
        model.addAttribute("categories", ProductCategory.values());
        return "products";
    }
}
