package com.example.PaymentService.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

@Configuration
public class RabbitMQConfig {


    public static final String EXCHANGE_NAME = "payment.exchange";
    public static final String PAYMENT_SUCCESS_ROUTING_KEY = "ticket_payment_success";
    public static final String PENALTY_ROUTING_KEY = "penalty_charged";

    public RabbitMQConfig() {
    }

    @Bean
    public TopicExchange paymentExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue paymentSuccessQueue() {
        return new Queue("payment.success.queue");
    }

    @Bean
    public Queue penaltyQueue() {
        return new Queue("penalty.queue");
    }

    @Bean
    public Binding bindingPaymentSuccess(Queue paymentSuccessQueue, TopicExchange paymentExchange) {
        return BindingBuilder.bind(paymentSuccessQueue).to(paymentExchange).with(PAYMENT_SUCCESS_ROUTING_KEY);
    }

    @Bean
    public Binding bindingPenalty(Queue penaltyQueue, TopicExchange paymentExchange) {
        return BindingBuilder.bind(penaltyQueue).to(paymentExchange).with(PENALTY_ROUTING_KEY);
    }

    // Configure a JSON message converter for RabbitMQ
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

