package io.github.andrehsvictor.deck.service.shared.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    Queue cardCreatedQueue() {
        return QueueBuilder
                .durable("q.card.created")
                .withArgument("x-dead-letter-exchange", "dlx.card")
                .build();
    }

    @Bean
    Queue cardDeletedQueue() {
        return QueueBuilder
                .durable("q.card.deleted")
                .withArgument("x-dead-letter-exchange", "dlx.card")
                .build();
    }

    @Bean
    DirectExchange cardExchange() {
        return new DirectExchange("ex.card");
    }

    @Bean
    Binding cardCreatedBinding() {
        return BindingBuilder
                .bind(cardCreatedQueue())
                .to(cardExchange())
                .with("card.created");
    }

    @Bean
    Binding cardDeletedBinding() {
        return BindingBuilder
                .bind(cardDeletedQueue())
                .to(cardExchange())
                .with("card.deleted");
    }

    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
