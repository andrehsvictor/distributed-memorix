package io.github.andrehsvictor.card.service.shared.dto.card;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardCreationEventDto implements Serializable {
    
    private static final long serialVersionUID = 2171806574968931005L;

    private String cardId;
    private String deckId;
}
