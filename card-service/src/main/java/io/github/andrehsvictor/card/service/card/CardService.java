package io.github.andrehsvictor.card.service.card;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.andrehsvictor.card.service.deck.DeckService;
import io.github.andrehsvictor.card.service.shared.dto.card.CardCreationEventDto;
import io.github.andrehsvictor.card.service.shared.dto.card.PostCardDto;
import io.github.andrehsvictor.card.service.shared.dto.card.PutCardDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final DeckService deckService;
    private final RabbitTemplate rabbitTemplate;

    @Transactional
    public Card create(UUID deckId, PostCardDto postCardDto) {
        if (!deckService.existsById(deckId)) {
            throw new IllegalArgumentException("Deck with id " + deckId + " does not exist");
        }
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

    @Transactional
    public Card update(UUID id, PutCardDto putCardDto) {
        Card card = findById(id);
        cardMapper.updateCardFromPutCardDto(card, putCardDto);
        card = cardRepository.save(card);
        return card;
    }

    @Transactional
    public void delete(UUID id) {
        cardRepository.deleteById(id);
        rabbitTemplate.convertAndSend("q.card.deleted", id);
    }

    @Transactional
    public void deleteByDeckId(UUID deckId) {
        cardRepository.deleteAllByDeckId(deckId);
    }

    public Page<Card> findAllByDeckId(UUID deckId, Pageable pageable) {
        if (!deckService.existsById(deckId)) {
            throw new IllegalArgumentException("Deck with id " + deckId + " does not exist");
        }
        return cardRepository.findAllByDeckId(deckId, pageable);
    }

    public Card findById(UUID id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Card with id " + id + " does not exist"));
    }

    public Page<Card> findAll(Pageable pageable) {
        return cardRepository.findAll(pageable);
    }

}
