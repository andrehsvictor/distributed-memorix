package io.github.andrehsvictor.card.service.shared.dto.card;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardCreationEventDto {
    private UUID cardId;
    private UUID deckId;
}
