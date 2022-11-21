package edu.azati.shop.controller;

import edu.azati.shop.activemq.JmsProducer;
import edu.azati.shop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProduceMessageController {
    @Autowired
    JmsProducer jmsProducer;

    @GetMapping("/user")
    public String showForm(User user) {
        return "send-user";
    }
}
