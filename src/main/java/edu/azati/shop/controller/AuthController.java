package edu.azati.shop.controller;

import edu.azati.shop.dto.UserDto;
import edu.azati.shop.entity.User;
import edu.azati.shop.error.UserAlreadyExistException;
import edu.azati.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String loginUser(Model model) {
        return "login";
    }

    @GetMapping("/success-register")
    public String successRegistration(Model model) {
        return "success-register";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "register";
    }

    @PostMapping("/process-register")
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserDto userDto) {
        ModelAndView mav = new ModelAndView();
        try {
            User registered = userService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistException uaeEx) {
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        }
        return new ModelAndView("success-register", "user", userDto);
    }
    @GetMapping("/success-login")
    public String successLogin(Model model) {
        return "success-login";
    }
}
