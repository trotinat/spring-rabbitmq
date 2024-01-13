package com.example.consumer.service;

import com.example.consumer.domain.User;
import com.example.consumer.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @Autowired
    public ConsumerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(User user) {
        User save = userRepository.save(user);
        logger.info("persisted " + save);
        logger.info("User recieved: " + user);
    }
}
