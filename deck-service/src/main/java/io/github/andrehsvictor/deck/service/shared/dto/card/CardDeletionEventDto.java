package io.github.andrehsvictor.deck.service.shared.dto.card;

import java.io.Serializable;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardDeletionEventDto implements Serializable {

    private static final long serialVersionUID = 6368682656774353106L;
    
    private UUID cardId;
    private UUID deckId;
}
