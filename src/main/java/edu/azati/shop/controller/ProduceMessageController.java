package edu.azati.shop.controller;

import edu.azati.shop.activemq.JmsProducer;
import edu.azati.shop.entity.Manufacturer;
import edu.azati.shop.entity.User;
import edu.azati.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class ProduceMessageController {
    @Autowired
    JmsProducer jmsProducer;

    @GetMapping("/user")
    public String showForm(User user) {
        return "send-user";
    }
}
