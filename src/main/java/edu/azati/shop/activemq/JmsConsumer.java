package edu.azati.shop.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsConsumer {
    @JmsListener(destination = "queue.user.out")
    public void receiveMessage(String message) {

        try {
            System.out.println("Received Message: " + message);
        } catch (Exception e) {
            System.out.println("Received Exception : " + e);
        }
    }
}
