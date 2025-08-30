package io.github.andrehsvictor.deck.service.shared.dto.card;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardCreationEventDto implements Serializable {

    private static final long serialVersionUID = 1701261914595385753L;
    
    private String cardId;
    private String deckId;

}
