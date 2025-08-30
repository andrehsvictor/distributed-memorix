package io.github.andrehsvictor.deck.service.deck;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeckRepository extends JpaRepository<Deck, UUID> {

}
