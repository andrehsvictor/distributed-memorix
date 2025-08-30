package io.github.andrehsvictor.deck.service.dto.deck;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PutDeckDto {

    private String name;
    private String description;

}
