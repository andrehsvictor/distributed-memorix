package io.github.andrehsvictor.deck.service.deck;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DeckRepository extends JpaRepository<Deck, UUID> {

    @Query("""
            SELECT d 
            FROM Deck d
            WHERE (LOWER(:name) IS NULL OR LOWER(d.name) = LOWER(:name))
            AND (LOWER(:description) IS NULL OR LOWER(d.description) LIKE LOWER(CONCAT('%', :description, '%')))
            """)
    Page<Deck> findAllWithFilters(
            String name,
            String description,
            Pageable pageable);

}