package io.github.andrehsvictor.card.service.card;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import io.github.andrehsvictor.card.service.shared.dto.card.CardCreationEventDto;
import io.github.andrehsvictor.card.service.shared.dto.card.PostCardDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final RabbitTemplate rabbitTemplate;

    public Card create(UUID deckId, PostCardDto postCardDto) {
        Card card = cardMapper.postCardDtoToCard(postCardDto);
        card.setDeckId(deckId);
        card = cardRepository.save(card);
        CardCreationEventDto cardCreationEventDto = CardCreationEventDto.builder()
                .cardId(card.getId())
                .deckId(deckId)
                .build();
        rabbitTemplate.convertAndSend("q.card.created", cardCreationEventDto);
        return card;
    }

}
