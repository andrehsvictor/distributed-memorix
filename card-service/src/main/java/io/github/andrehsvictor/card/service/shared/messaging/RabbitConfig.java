package io.github.andrehsvictor.card.service.shared.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    Queue deckDeletedQueue() {
        return QueueBuilder
                .durable("q.deck.deleted")
                .withArgument("x-dead-letter-exchange", "dlx.deck")
                .build();
    }

    @Bean
    DirectExchange deckExchange() {
        return new DirectExchange("ex.deck");
    }

    @Bean
    Binding deckDeletedBinding() {
        return BindingBuilder
                .bind(deckDeletedQueue())
                .to(deckExchange())
                .with("deck.deleted");
    }

    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }
}
