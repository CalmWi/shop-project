package edu.azati.shop.activemq;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.azati.shop.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
public class JmsProducer {
    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    public void sendMessage(String message) {
        try {
            System.out.println("Attempting Send message: " + queue);
            jmsTemplate.convertAndSend(queue, message);
        } catch (Exception e) {
            System.out.println("Recieved Exception during send Message: " + e);
        }
    }
    /*public void sendMessage(User user) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String userAsJson = mapper.writeValueAsString(user);
            System.out.println("Attempting Send message: " + queue);
            jmsTemplate.convertAndSend(queue, userAsJson);
        } catch (Exception e) {
            System.out.println("Recieved Exception during send Message: " + e);
        }
    }*/
}
