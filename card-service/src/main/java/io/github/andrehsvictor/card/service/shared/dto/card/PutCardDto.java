package io.github.andrehsvictor.card.service.shared.dto.card;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PutCardDto {
    private String question;
    private String answer;
}
