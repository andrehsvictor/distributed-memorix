package io.github.andrehsvictor.card.service.shared.dto.card;

import java.io.Serializable;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardDeletionEventDto implements Serializable {

    private static final long serialVersionUID = 1655786129530689179L;
    
    private UUID cardId;
    private UUID deckId;

}
