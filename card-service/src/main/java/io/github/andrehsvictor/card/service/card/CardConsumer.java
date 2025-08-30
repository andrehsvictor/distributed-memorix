package io.github.andrehsvictor.card.service.card;

import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardConsumer {

    private final CardService cardService;

    @RabbitListener(queues = { "q.deck.deleted" })
    public void handleDeckDeletedEvent(String deckId) {
        cardService.deleteByDeckId(UUID.fromString(deckId));
    }

}
