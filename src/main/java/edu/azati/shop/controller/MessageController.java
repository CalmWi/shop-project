package edu.azati.shop.controller;

import edu.azati.shop.activemq.JmsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired
    JmsProducer jmsProducer;

    @PostMapping(value = "/send")
    public ResponseEntity<String> send(@RequestBody String message) {
        jmsProducer.sendMessage(message);
        return new ResponseEntity<>("Message send: " + message, HttpStatus.OK);
    }

}
