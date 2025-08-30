package io.github.andrehsvictor.deck.service.shared.dto.card;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardDeletionEventDto {
    private UUID cardId;
    private UUID deckId;
}
