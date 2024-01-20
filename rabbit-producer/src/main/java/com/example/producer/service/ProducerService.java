package com.example.producer.service;


import com.example.producer.domain.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    private RabbitTemplate rabbitTemplate;



    @Autowired
    public ProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    //@Value("${spring.rabbitmq.routingkey}")
    //private String routingkey;

    public void sendMessage(User user) {
        rabbitTemplate.convertAndSend(exchange,"user.routingkey", user);
    }
    public void sendMessageC2(User user){rabbitTemplate.convertAndSend(exchange,"user.routingkey1",user);}
}
