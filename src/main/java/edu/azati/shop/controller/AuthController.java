package edu.azati.shop.controller;

import edu.azati.shop.dto.UserDto;
import edu.azati.shop.entity.User;
import edu.azati.shop.error.UserAlreadyExistException;
import edu.azati.shop.security.SecurityUser;
import edu.azati.shop.security.jwt.JwtTokenProvider;
import edu.azati.shop.services.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/process-login")
    public ModelAndView authenticateUser(@ModelAttribute("user") @Valid @NotNull UserDto userDto, HttpServletResponse response){

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.generateJwtToken(authentication);
            Cookie cookie = new Cookie("jwt", jwt);
            cookie.setHttpOnly(true);
            cookie.setPath("/api");
            cookie.setMaxAge(1 * 24 * 60 * 60);
            response.addCookie(cookie);

        }catch (AuthenticationException e){
            ModelAndView mav = new ModelAndView();
            mav.addObject("message", "Invalid email/password combination");
            return mav;
        }
        return new ModelAndView("success-login", "user", userDto);
    }
    @PostMapping("/logout")
    public String logout(HttpServletResponse response){
        Cookie cookie = new Cookie("jwt", null);
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        cookie.setPath("/api");
        response.addCookie(cookie);
        return "redirect:/api/auth/login";
    }
    @GetMapping("/login")
    public String loginUser(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
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
        try {
            User registered = userService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistException uaeEx) {
            ModelAndView mav = new ModelAndView();
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
