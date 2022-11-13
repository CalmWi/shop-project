package edu.azati.shop.controller;

import edu.azati.shop.entity.User;
import edu.azati.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String showUser(Model model) {
        return "index";
    }

    @GetMapping("/register")
    public String registerUser(Model model) {
        return "register";
    }

    @GetMapping("/login")
    public String loginUser(Model model) {
        return "login";
    }

    @GetMapping("/account")
    public String showUserAccount(Model model) {
        return "account";
    }

    @GetMapping("/account/details")
    public String showAccountDetails(Model model) {
        return "account-details";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    /*@PostMapping("/send-user")
    public String sendMessage(@Validated User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "send-user";
        }
        return "users";
    }*/
    @GetMapping("/signup-user")
    public String showSignUpForm(User user) {
        return "add-user";
    }

    @PostMapping("/add-user")
    public String addUser(@Validated User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit-user/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/update-user/{id}")
    public String updateUser(@PathVariable("id") long id, @Validated User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }
        userService.updateUser(user.getId(), user.getName(), user.getSurname(), user.getPatronymic(), user.getUserRole());
        return "redirect:/users";
    }

    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userService.getUserById(id);
        userService.deleteUserById(user.getId());
        return "redirect:/users";
    }
}
