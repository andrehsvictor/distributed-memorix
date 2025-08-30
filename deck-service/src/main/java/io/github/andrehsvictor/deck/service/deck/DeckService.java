package io.github.andrehsvictor.deck.service.deck;

import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.andrehsvictor.deck.service.shared.dto.deck.PostDeckDto;
import io.github.andrehsvictor.deck.service.shared.dto.deck.PutDeckDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeckService {

    private final DeckRepository deckRepository;
    private final DeckMapper deckMapper;
    private final RabbitTemplate rabbitTemplate;

    public Page<Deck> findAllWithFilters(
            String title,
            String description,
            Instant createdAtStart,
            Instant createdAtEnd,
            Instant updatedAtStart,
            Instant updatedAtEnd,
            Pageable pageable) {
        return deckRepository.findAllWithFilters(
                title,
                description,
                createdAtStart,
                createdAtEnd,
                updatedAtStart,
                updatedAtEnd,
                pageable);
    }

    public Deck persist(Deck deck) {
        return deckRepository.save(deck);
    }

    public Deck findById(UUID id) throws NoSuchElementException {
        return deckRepository.findById(id).orElseThrow();
    }

    public boolean existsById(UUID id) {
        return deckRepository.existsById(id);
    }

    @Transactional
    public Deck create(PostDeckDto postDeckDto) {
        Deck deck = deckMapper.postDeckDtoToDeck(postDeckDto);
        return deckRepository.save(deck);
    }

    @Transactional
    public Deck update(UUID id, PutDeckDto putDeckDto) throws NoSuchElementException {
        Deck deck = deckRepository.findById(id).orElseThrow();
        deckMapper.updateDeckFromPutDeckDto(putDeckDto, deck);
        return deckRepository.save(deck);
    }

    @Transactional
    public void delete(UUID id) throws NoSuchElementException {
        Deck deck = deckRepository.findById(id).orElseThrow();
        deckRepository.delete(deck);
        rabbitTemplate.convertAndSend("q.deck.deleted", id);
    }

}
