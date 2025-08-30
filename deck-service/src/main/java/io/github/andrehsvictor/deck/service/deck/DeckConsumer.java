package io.github.andrehsvictor.deck.service.deck;

import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import io.github.andrehsvictor.deck.service.shared.dto.card.CardCreationEventDto;
import io.github.andrehsvictor.deck.service.shared.dto.card.CardDeletionEventDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeckConsumer {

    private final DeckService deckService;

    @RabbitListener(queues = "q.card.deleted")
    public void handleCardDeletionEvent(CardDeletionEventDto cardDeletionEventDto) {
        Deck deck = deckService.findById(UUID.fromString(cardDeletionEventDto.getDeckId()));
        deck.setCardsCount(deck.getCardsCount() - 1);
        deckService.persist(deck);
    }

    @RabbitListener(queues = "q.card.created")
    public void handleCardCreationEvent(CardCreationEventDto cardCreationEventDto) {
        Deck deck = deckService.findById(UUID.fromString(cardCreationEventDto.getDeckId()));
        deck.setCardsCount(deck.getCardsCount() + 1);
        deckService.persist(deck);
    }

}
