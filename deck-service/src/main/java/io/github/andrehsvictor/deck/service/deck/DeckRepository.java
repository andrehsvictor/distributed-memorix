package io.github.andrehsvictor.deck.service.deck;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeckRepository extends JpaRepository<Deck, UUID> {

    @Query("""
            SELECT d FROM Deck d
            WHERE (:title IS NULL OR LOWER(d.title) LIKE LOWER(CONCAT('%', :title, '%')))
            AND (:description IS NULL OR LOWER(d.description) LIKE LOWER(CONCAT('%', :description, '%')))
            AND (:createdAtStart IS NULL OR d.createdAt >= :createdAtStart)
            AND (:createdAtEnd IS NULL OR d.createdAt <= :createdAtEnd)
            AND (:updatedAtStart IS NULL OR d.updatedAt >= :updatedAtStart)
            AND (:updatedAtEnd IS NULL OR d.updatedAt <= :updatedAtEnd)
            ORDER BY d.createdAt DESC
            """)
    Page<Deck> findAllWithFilters(
            @Param("title") String title,
            @Param("description") String description,
            @Param("createdAtStart") Instant createdAtStart,
            @Param("createdAtEnd") Instant createdAtEnd,
            @Param("updatedAtStart") Instant updatedAtStart,
            @Param("updatedAtEnd") Instant updatedAtEnd,
            Pageable pageable);

}