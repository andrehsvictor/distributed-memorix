package io.github.andrehsvictor.card.service.card;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CardRepository extends MongoRepository<Card, UUID> {

    Page<Card> findAllByDeckId(UUID deckId, Pageable pageable);

    Long deleteAllByDeckId(UUID deckId);

}
